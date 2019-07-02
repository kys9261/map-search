package com.kys9261.mapsearch.model.kakao.location;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KakaoLocal {

    @SerializedName("address_name")
    private String addressName;

    @SerializedName("category_group_code")
    private String categoryGroupCode;

    @SerializedName("category_group_name")
    private String categoryGroupName;

    @SerializedName("category_name")
    private String categoryName;

    private String distance;

    private String id;

    private String phone;

    @SerializedName("place_name")
    private String placeName;

    @SerializedName("place_url")
    private String placeUrl;

    @SerializedName("road_address_name")
    private String roadAddressName;

    private String x;

    private String y;
}
