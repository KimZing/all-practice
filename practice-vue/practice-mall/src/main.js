import Vue from 'vue'
import App from './App.vue'
import Axios from 'axios'
import VueAxios from 'vue-axios'
import Router from './router'
import './assets/css/base.css'
import './assets/css/index.css'

Vue.use(VueAxios, Axios);
//提示开关
Vue.config.productionTip = false

new Vue({
  // 如果一样可以简写为router,
  router: Router,
  render: h => h(App),
}).$mount('#app')
