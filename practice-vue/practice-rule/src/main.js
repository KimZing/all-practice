import Vue from "vue";
import App from "./App.vue";
import Axios from "axios";
import VueAxios from "vue-axios";
import router from "./router";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import "./assets/css/main.css";

Axios.defaults.baseURL=process.env.VUE_APP_API_SERVER;

Axios.interceptors.request.use(
  config => {
    config.headers.token = sessionStorage.getItem("token");
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);

Vue.use(ElementUI);
Vue.use(VueAxios, Axios);
Vue.config.productionTip = false;

new Vue({
  router,
  render: h => h(App)
}).$mount("#app");
