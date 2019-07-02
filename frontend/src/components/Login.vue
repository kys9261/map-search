<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <form @submit.prevent="login">
      <b-form-group label="Enter your user id">
        <b-form-input v-model="userId" type="text" placeholder="아이디 입력"></b-form-input>
      </b-form-group>
      <b-form-group label="Enter your password">
        <b-form-input v-model="password" type="password" placeholder="비밀번호 입력"></b-form-input>
      </b-form-group>
      <b-button size="lg" variant="success" type="submit">로그인</b-button>
    </form>
  </div>
</template>

<script>
  import axios from 'axios';

  export default {
    name: 'Login',
    data () {
      return {
        msg: 'Location Search Web Application',
        userId: '',
        password: '',
      };
    },
    methods: {
      login () {
        const userId = this.userId;
        const password = this.password;

        if (!userId || !password) {
          return false;
        }

        axios.post('http://localhost:8080/api/user/login', { userId, password })
          .then(res => {
            if (res.status === 200) {
              alert('로그인 성공');
              document.cookie = `JWT_TOKEN=${res.data.response.jwtToken}`;
              axios.defaults.headers.common['x-access-token'] = res.data.response.jwtToken;
              this.$router.push({ name: 'Home' });
            }
          })
          .catch(err => {
            alert('로그인 실패');
          })
      }
    }
  };
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .hello {
    width: 1024px;
    margin: auto;
  }
  h1, h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }

  .btn-lg {
    width: 100%;
  }
</style>
