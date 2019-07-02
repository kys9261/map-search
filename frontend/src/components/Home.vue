<template>
  <div>
    <div id="searchSection">
      <b-form-group label="">
        <b-form-input class="input" v-model="placeName" type="text" placeholder="검색할 장소를 입력하세요"></b-form-input>
      </b-form-group>
      <b-button size="sm" v-on:click="searchPlace">검색</b-button>
    </div>
    <h2>{{ searchPlaceName }} 검색결과 <span v-if="items.length > 0"> : {{ totalItems }} 건</span></h2>


    <table class="table b-table table-striped table-hover">
      <thead>
      <tr>
        <th>번호</th>
        <th>이름</th>
        <th>주소</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="item, i in items" v-if="items.length > 0" @click="locationDetail(item)">
        <td>{{ (i+1) + (currentPage-1) * 10 }}</td>
        <td>{{ item.placeName }}</td>
        <td>{{ item.address }}</td>
      </tr>

      <tr v-if="items.length == 0">
        <td colspan="3">검색된 내용이 없습니다.</td>
      </tr>
      </tbody>
    </table>

    <b-pagination v-on:input="onPageChange" v-if="items.length > 0" v-model="currentPage" :total-rows="totalItems" :per-page="perPage" aria-controls="my-table" align="center"></b-pagination>

    <div>
      <div class="table-left">
        <h2>검색 히스토리</h2>
        <table class="table b-table table-striped table-hover">
          <thead>
            <tr>
              <td>순위</td>
              <td>키워드</td>
              <td>검색 일시</td>
            </tr>
          </thead>

          <tr v-for="item, i in history">
            <td width="20%">{{ i+1 }}</td>
            <td>{{ item.keyword }}</td>
            <td>{{ item.createAt | moment("YYYY-MM-DD hh:mm:ss") }}</td>
          </tr>
        </table>
      </div>
      <div class="table-right">
        <h2>인기 키워드 목록</h2>
        <table class="table b-table table-striped table-hover">
          <thead>
          <tr>
            <td>순위</td>
            <td>키워드</td>
            <td>조회 수</td>
          </tr>
          </thead>

          <tr v-for="item, i in popular">
            <td width="20%">{{ i+1 }}</td>
            <td>{{ item.keyword }}</td>
            <td>{{ item.count }} 회</td>
          </tr>
        </table>
      </div>
    </div>

    <b-modal ref="locationDetail" id="locationDetail" title="상세 정보" ok-only>
      <table id="modal-table" border="1">
          <tr>
            <td>상호명</td>
            <td>{{ selectItem.placeName }}</td>
          </tr>

          <tr>
            <td>지번</td>
            <td>{{ selectItem.addressName }}</td>
          </tr>

          <tr>
            <td>도로명주소</td>
            <td>{{ selectItem.roadAddressName }}</td>
          </tr>

          <tr>
            <td>전화번호</td>
            <td>{{ selectItem.phone }}</td>
          </tr>

          <tr>
            <td>지도 바로가기</td>
            <td><a v-bind:href="'https://map.kakao.com/link/to/'+ selectItem.id" target="_blank">링크</a></td>
          </tr>

          <tr>
            <td colspan="2">
              <div id="map" style="width:462px;height:400px;"></div>
            </td>
          </tr>
      </table>
    </b-modal>

  </div>
</template>

<script>
  import axios from 'axios';
  import cookieJs from 'js-cookie';

  export default {
    name: 'Home',
    created () {
      axios.defaults.headers.common['x-access-token'] = cookieJs.get('JWT_TOKEN');
      this.historyKeywordList();
      this.popularKeywordList();
    },
    data () {
      return {
        placeName: '',
        searchPlaceName: '',
        currentPage: 1,
        totalItems: 0,
        perPage: 10,
        items: [],
        selectItem: '',
        history: [],
        popular: []
      }
    },
    methods: {
      historyKeywordList: function () {
        axios.get('http://localhost:8080/api/keyword/history')
          .then(res => {
            if(res.status === 200) {
              this.history = res.data.response;
            }
          })
          .catch(err => {
            console.log(err);
          })
      },
      popularKeywordList: function () {
        axios.get('http://localhost:8080/api/keyword/popular')
          .then(res => {
            if(res.status === 200) {
              this.popular = res.data.response;
            }
          })
          .catch(err => {
            console.log(err);
          });
      },
      searchPlace: function (){
        this.searchPlaceName = this.placeName;
        axios.get('http://localhost:8080/api/location/list?placeName='+this.placeName+'&page=1')
          .then(res => {
            if(res.status === 200) {
              this.items = res.data.response.documents;
              this.totalItems = res.data.response.meta.totalCount;
              this.currentPage = 1;

              this.historyKeywordList();
              this.popularKeywordList();
            }
          })
          .catch(err => {
            alert('검색에 실패했습니다.');
          })
      },
      onPageChange: function () {
        axios.get('http://localhost:8080/api/location/list?placeName='+this.placeName+'&page='+this.currentPage)
          .then(res => {
            if(res.status === 200) {
              this.items = res.data.response.documents;
            }
          })
          .catch(err => {
            alert('검색에 실패했습니다.');
          })
      },
      locationDetail: function (item) {
        this.selectItem = item;
        this.$refs['locationDetail'].show();

        setTimeout(function () {
          var container = document.getElementById('map');
          var options = {
            center: new kakao.maps.LatLng(item.y, item.x),
            level: 3
          };
          var map = new kakao.maps.Map(container, options);
          var marker = new kakao.maps.Marker({
            map: map,
            position: new kakao.maps.LatLng(item.y, item.x)
          });

          var content = '<div class="wrap">' +
            '    <div class="info">' +
            '        <div class="title">' + item.placeName +
            '            <div class="close" onclick="closeOverlay()" title="닫기"></div>' +
            '        </div>' +
            '        <div class="body">' +
            '            <div class="img">' +
            '                <img src="http://cfile181.uf.daum.net/image/250649365602043421936D" width="73" height="70">' +
            '           </div>' +
            '            <div class="desc">' +
            '                <div class="ellipsis">'+item.addressName+'</div>' +
            '                <div class="ellipsis">'+item.roadAddressName+'</div>' +
            '                <div class="ellipsis">'+item.phone+'</div>' +
            '            </div>' +
            '        </div>' +
            '    </div>' +
            '</div>';

          var overlay = new kakao.maps.CustomOverlay({
            content: content,
            map: map,
            position: marker.getPosition()
          });

          kakao.maps.event.addListener(marker, 'click', function() {
            overlay.setMap(map);
          });

          function closeOverlay() {
            overlay.setMap(null);
          }        },500);
      }
    },
    mounted() {
      let daumMapScript = document.createElement('script')
      daumMapScript.setAttribute('src', 'https://dapi.kakao.com/v2/maps/sdk.js?appkey=59dadc28efb1a5da852fb157bc69569c')
      document.head.appendChild(daumMapScript)

      let kakaoScript = document.createElement('script')
      kakaoScript.setAttribute('src', 'https://t1.daumcdn.net/mapjsapi/js/main/4.1.5/kakao.js')
      document.head.appendChild(kakaoScript)
    }
  }
</script>

<style>
.form-group {
  display: inline-block;
}

#searchSection {
  display: inline-block;
}

#modal-table {
  width: 100%;
  text-align: center;
}

.table-left, .table-right {
  width: 49%;
}

.table-left {
  float: left;
}

.table-right {
  float: right;
}

.wrap {position: absolute;left: 0;bottom: 40px;width: 288px;height: 132px;margin-left: -144px;text-align: left;overflow: hidden;font-size: 12px;font-family: 'Malgun Gothic', dotum, '돋움', sans-serif;line-height: 1.5;}
.wrap * {padding: 0;margin: 0;}
.wrap .info {width: 286px;height: 120px;border-radius: 5px;border-bottom: 2px solid #ccc;border-right: 1px solid #ccc;overflow: hidden;background: #fff;}
.wrap .info:nth-child(1) {border: 0;box-shadow: 0px 1px 2px #888;}
.info .title {padding: 5px 0 0 10px;height: 30px;background: #eee;border-bottom: 1px solid #ddd;font-size: 18px;font-weight: bold;}
.info .close {position: absolute;top: 10px;right: 10px;color: #888;width: 17px;height: 17px;background: url('http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/overlay_close.png');}
.info .close:hover {cursor: pointer;}
.info .body {position: relative;overflow: hidden;}
.info .desc {position: relative;margin: 13px 0 0 90px;height: 75px;}
.desc .ellipsis {overflow: hidden;text-overflow: ellipsis;white-space: nowrap;}
.desc .jibun {font-size: 11px;color: #888;margin-top: -2px;}
.info .img {position: absolute;top: 6px;left: 5px;width: 73px;height: 71px;border: 1px solid #ddd;color: #888;overflow: hidden;}
.info:after {content: '';position: absolute;margin-left: -12px;left: 50%;bottom: 0;width: 22px;height: 12px;background: url('http://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png')}
.info .link {color: #5085BB;}
.close {display: none;}
</style>
