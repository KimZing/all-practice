import Vue from "vue";
import Router from "vue-router";
import RuleQuery from "./pages/rule-query";
import Upgrade from "./pages/upgrade";
import RuleSave from "./pages/rule-save";
import Param from "./pages/param";
import Device from "./pages/device";
import Main from "./pages/main";
import Login from "./pages/login";

Vue.use(Router);

export default new Router({
  mode: "hash",
  routes: [
    {
      path: "/",
      component: Login
    },
    {
      path: "/",
      meta: {
        auth: true
      },
      component: Main,
      redirect: "/rule/query",
      children: [
        {
          path: "rule/query",
          component: RuleQuery
        },
        {
          path: "upgrade",
          component: Upgrade
        },
        {
          name: "RuleSavePage",
          path: "rule/save",
          component: RuleSave
        },
        {
          path: "param",
          component: Param
        },
        {
          path: "device",
          component: Device
        }
      ]
    }
  ]
});
