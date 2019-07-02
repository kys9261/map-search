import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login.vue'
import Home from '@/components/Home.vue'
import VueCookies from 'vue-cookies'

Vue.use(Router)
Vue.use(VueCookies)

const requireAuth = () => (to, from, next) => {
  var access_token = window.$cookies.get('JWT_TOKEN')
  if(access_token != null) {
    return next()
  }
  next('/')
}

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/home',
      name: 'Home',
      component: Home,
      beforeEnter: requireAuth()
    },
    {
      path: '/',
      name: 'Login',
      component: Login
    }
  ]
})
