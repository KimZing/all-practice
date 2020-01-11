import Vue from "vue";
import Router from "vue-router";
import Cart from "./pages/cart";
import Addr from "./pages/addr";

//添加进Vue才能生效
Vue.use(Router);

// 导出一个router对象, 创建不同页面的路由(页面仍然是一个组件，只是我们定义为页面)
export default new Router({
  routes: [
    {
      path: "/",
      name: "cart",
      // 元数据，可以做校验等
      meta: {
        auth: true
      },
      component: Cart
    },
    {
      path: "/addr",
      name: "addr",
      component: Addr
    }
  ]
});
