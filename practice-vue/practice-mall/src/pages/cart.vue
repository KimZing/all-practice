<template>
  <div>
    <nav-header></nav-header>
    <!-- 购物车商品内容 -->
    <div class="nav-breadcrumb-wrap">
      <div class="container">
        <nav class="nav-breadcrumb">
          <a href="/">首页</a>
          <span>购物车</span>
        </nav>
      </div>
    </div>
    <!-- 定义购物车页面图标 -->
    <svg
      style="position: absolute; width: 0; height: 0; overflow: hidden;"
      version="1.1"
      xmlns="http://www.w3.org/2000/svg"
      xmlns:xlink="http://www.w3.org/1999/xlink"
    >
      <defs>
        <symbol id="icon-ok" viewBox="0 0 32 32">
          <title>ok</title>
          <path
            class="path1"
            d="M31.020 0.438c-0.512-0.363-1.135-0.507-1.757-0.406s-1.166 0.435-1.529 0.937l-17.965 24.679-5.753-5.67c-0.445-0.438-1.035-0.679-1.664-0.679s-1.219 0.241-1.664 0.679c-0.917 0.904-0.917 2.375 0 3.279l7.712 7.6c0.438 0.432 1.045 0.681 1.665 0.681l0.195-0.008c0.688-0.057 1.314-0.406 1.717-0.959l19.582-26.9c0.754-1.038 0.512-2.488-0.538-3.233z"
          />
        </symbol>
        <symbol id="icon-del" viewBox="0 0 32 32">
          <title>delete</title>
          <path
            class="path1"
            d="M11.355 4.129v-2.065h9.29v2.065h-9.29zM6.194 29.935v-23.742h19.613v23.742h-19.613zM30.968 4.129h-8.258v-3.097c0-0.569-0.463-1.032-1.032-1.032h-11.355c-0.569 0-1.032 0.463-1.032 1.032v3.097h-8.258c-0.569 0-1.032 0.463-1.032 1.032s0.463 1.032 1.032 1.032h3.097v24.774c0 0.569 0.463 1.032 1.032 1.032h21.677c0.569 0 1.032-0.463 1.032-1.032v-24.774h3.097c0.569 0 1.032-0.463 1.032-1.032s-0.463-1.032-1.032-1.032v0z"
          />
          <path
            class="path2"
            d="M10.323 9.806c-0.569 0-1.032 0.463-1.032 1.032v14.452c0 0.569 0.463 1.032 1.032 1.032s1.032-0.463 1.032-1.032v-14.452c0-0.569-0.463-1.032-1.032-1.032z"
          />
          <path
            class="path3"
            d="M16 9.806c-0.569 0-1.032 0.463-1.032 1.032v14.452c0 0.569 0.463 1.032 1.032 1.032s1.032-0.463 1.032-1.032v-14.452c0-0.569-0.463-1.032-1.032-1.032z"
          />
          <path
            class="path4"
            d="M21.677 9.806c-0.569 0-1.032 0.463-1.032 1.032v14.452c0 0.569 0.463 1.032 1.032 1.032s1.032-0.463 1.032-1.032v-14.452c0-0.569-0.463-1.032-1.032-1.032z"
          />
        </symbol>
      </defs>
    </svg>
    <div class="container">
      <div class="cart">
        <div class="page-title-normal">
          <h2 class="page-title-h2">
            <span>我的购物车</span>
          </h2>
        </div>
        <div class="item-list-wrap">
          <div class="cart-item">
            <div class="cart-item-head">
              <ul>
                <li>商品信息</li>
                <li>商品金额</li>
                <li>商品数量</li>
                <li>总金额</li>
                <li>编辑</li>
              </ul>
            </div>
            <ul class="cart-item-list">
              <li v-for="item in carList" :key="item.productId">
                <div class="cart-tab-1">
                  <div class="cart-item-check">
                    <a
                      href="javascipt:;"
                      class="checkbox-btn item-check-btn"
                      :class="{checked: item.checked}"
                      @click="modifyCart('checked', item)"
                    >
                      <svg class="icon icon-ok">
                        <use xlink:href="#icon-ok" />
                      </svg>
                    </a>
                  </div>
                  <div class="cart-item-pic">
                    <img :src="'/images/' + item.productImage" />
                  </div>
                  <div class="cart-item-title">
                    <div class="item-name">{{item.productName}}</div>
                  </div>
                </div>
                <div class="cart-tab-2">
                  <div class="item-price">{{item.productPrice}}</div>
                </div>
                <div class="cart-tab-3">
                  <div class="item-quantity">
                    <div class="select-self select-self-open">
                      <div class="select-self-area">
                        <a class="input-sub" v-on:click="modifyCart('minus', item)">-</a>
                        <span class="select-ipt">{{item.productNum}}</span>
                        <a class="input-add" v-on:click="modifyCart('add', item)">+</a>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="cart-tab-4">
                  <div class="item-price-total">{{item.productNum * item.productPrice | money}}</div>
                </div>
                <div class="cart-tab-5">
                  <div class="cart-item-opration">
                    <a href="javascript:;" class="item-edit-btn">
                      <svg class="icon icon-del" @click="showModal(item)">
                        <use xlink:href="#icon-del" />
                      </svg>
                    </a>
                  </div>
                </div>
              </li>
            </ul>
          </div>
        </div>
        <div class="cart-foot-wrap">
          <div class="cart-foot-inner">
            <div class="cart-foot-l">
              <div class="item-all-check" @click="changeChoose">
                <a href="javascipt:;">
                  <span class="checkbox-btn item-check-btn" :class="{'check': checkAllChoose}">
                    <svg class="icon icon-ok">
                      <use xlink:href="#icon-ok" />
                    </svg>
                  </span>
                  <span>全选</span>
                </a>
              </div>
            </div>
            <div class="cart-foot-r">
              <div class="item-total">
                总价:
                <span class="total-price">{{totalMoney|money}}</span>
              </div>
              <div class="btn-wrap">
                <a class="btn btn--red" :class="{'btn--dis': !checkChoosed}" @click="deal">结算</a>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <modal :mdShow="mdShow" @close="closeModal">
      <!-- 新的写法，使用v-slot:名称 -->
      <template v-slot:message>
        <p slot="message">你确认要删除此条数据吗?</p>
      </template>
      <template v-slot:btnGroup>
        <a class="btn btn--m" href="javascript:;" @click="delCarItem">确认</a>
        <!-- 可以调用方法进行关闭，也可以直接改变属性 -->
        <a class="btn btn--m btn--red" href="javascript:;" @click="mdShow=false">关闭</a>
      </template>

      <!-- 老的写法 -->
      <!-- <p slot="message">你确认要删除此条数据吗?</p>
      <div slot="btnGroup">
        <a class="btn btn--m" href="javascript:;">确认</a>
        <a class="btn btn--m btn--red" href="javascript:;" @click="mdShow=false">关闭</a>
      </div>-->
    </modal>
    <nav-footer></nav-footer>
  </div>
</template>

<script>
import NavHeader from "../components/Header";
import NavFooter from "../components/Footer";
import Modal from "../components/Modal";
export default {
  name: "cart",
  props: {},
  data() {
    return {
      carList: [],
      mdShow: false,
      delItem: {}
    };
  },
  computed: {
    // 计算总金额
    totalMoney: function() {
      var total = 0;
      this.carList.forEach(item => {
        if (item.checked) {
          total = total + item.productPrice * item.productNum;
        }
      });
      return total;
    },
    // 检查选中状态
    checkAllChoose() {
      // every每一个都需要满足
      return this.carList.every(item => {
        return item.checked;
      });
    },
    // 计算是否有选中的元素
    checkChoosed() {
      let flag = this.carList.some(item => {
        return item.checked;
      });
      return flag;
    }
  },
  components: {
    NavHeader,
    NavFooter,
    Modal
  },
  // 可以使用完整函数，也可以直接使用函数声名
  mounted: function() {
    this.initCart();
  },
  filters: {
    money(value) {
      if (!value) return "￥" + 0.0 + "元";
      return "￥" + value.toFixed(2) + "元";
    }
  },
  methods: {
    // 初始化购物车
    initCart() {
      this.axios.get("/mock/cart.json").then(response => {
        this.carList = response.data.data;
      });
    },
    // 购物车状态修改
    modifyCart: (type, item) => {
      // debugger;
      if (type == "minus") {
        item.productNum--;
      } else if (type == "add") {
        item.productNum++;
      } else {
        item.checked = !item.checked;
      }
    },
    // 显示确认框
    showModal(item) {
      this.delItem = item;
      this.mdShow = true;
    },
    //关闭确认框
    closeModal() {
      this.mdShow = false;
    },
    //删除购物车条目
    delCarItem() {
      let id = this.delItem.productId;
      this.carList.forEach((item, index) => {
        if (item.productId == id) {
          this.carList.splice(index, 1);
        }
      });
      this.mdShow = false;
    },
    // 全选与反选
    changeChoose() {
      let flag = !this.checkAllChoose;
      this.carList.forEach(item => {
        item.checked = flag;
      });
    },
    deal() {
      if (this.checkChoosed) {
        this.$router.push({
          path: "/addr"
        });
      }
    }
  }
};
</script>