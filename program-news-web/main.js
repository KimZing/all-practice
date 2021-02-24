import Vue from 'vue'
import App from './App'
import api from './common/api'

Vue.config.productionTip = false
// 将api绑定到对应的Vue实例上，方便使用
Vue.prototype.$api = api

App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()
