<template>
  <div>
    <!-- 面包屑 -->
    <div class="nav-breadcrumb-wrap">
      <nav-header></nav-header>
      <div class="container">
        <nav class="nav-breadcrumb">
          <a href="/">首页</a>
          <span>地址</span>
        </nav>
      </div>
    </div>
    <!-- 地址确认 -->
    <div class="checkout-page">
      <svg
        style="position: absolute; width: 0; height: 0; overflow: hidden;"
        version="1.1"
        xmlns="http://www.w3.org/2000/svg"
        xmlns:xlink="http://www.w3.org/1999/xlink"
      >
        <defs>
          <symbol id="icon-add" viewBox="0 0 31 32">
            <title>add</title>
            <path
              class="path1"
              d="M30.745 15.152h-14.382v-14.596c0-0.308-0.243-0.557-0.543-0.557s-0.543 0.249-0.543 0.557v14.596h-14.665c-0.3 0-0.543 0.249-0.543 0.557s0.243 0.557 0.543 0.557h14.665v15.177c0 0.307 0.243 0.557 0.543 0.557s0.543-0.249 0.543-0.557v-15.177h14.382c0.3 0 0.543-0.249 0.543-0.557s-0.243-0.557-0.543-0.557z"
            />
          </symbol>
          <symbol id="icon-edit" viewBox="0 0 32 32">
            <title>edit</title>
            <path
              class="path1"
              d="M28.287 8.51l-4.805-4.806 0.831-0.831c0.472-0.472 1.086-0.777 1.564-0.777 0.248 0 0.452 0.082 0.622 0.253l3.143 3.144c0.539 0.54 0.133 1.529-0.524 2.186l-0.831 0.831zM26.805 9.992l-1.138 1.138-4.805-4.806 1.138-1.138 4.805 4.806zM24.186 12.612l-14.758 14.762-4.805-4.806 14.758-14.762 4.805 4.806zM7.379 28.288l-4.892 1.224 1.223-4.894 3.669 3.67zM31.123 4.011l-3.143-3.144c-0.567-0.567-1.294-0.867-2.103-0.867-1.036 0-2.174 0.52-3.045 1.391l-20.429 20.436c-0.135 0.134-0.23 0.302-0.276 0.487l-2.095 8.385c-0.089 0.355 0.017 0.736 0.276 0.995 0.198 0.198 0.461 0.307 0.741 0.307 0.085 0 0.171-0.010 0.254-0.031l8.381-2.096c0.185-0.047 0.354-0.142 0.487-0.276l20.43-20.436c1.409-1.41 2.042-3.632 0.524-5.15v0z"
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
        <div class="checkout-addr">
          <div class="page-title-normal">
            <h2 class="page-title-h2">
              <span>结算</span>
            </h2>
          </div>
          <!-- process step -->
          <div class="check-step">
            <ul>
              <li class="cur">确认地址</li>
              <li>查看订单</li>
              <li>支付</li>
              <li>订单 确认</li>
            </ul>
          </div>

          <!-- address list -->
          <div class="page-title-normal checkout-title">
            <h2>
              <span>配送地址</span>
            </h2>
          </div>
          <div class="addr-list-wrap">
            <div class="addr-list">
              <ul>
                <li
                  :class="{'check': index == checkIndex}"
                  v-for="(item, index) in showAddressList"
                  :key="item.addressId"
                  @click="clickState(index)"
                >
                  <dl>
                    <dt>{{item.userName}}</dt>
                    <dd class="address">{{item.streetName + ' : ' + item.postCode}}</dd>
                    <dd class="tel">{{item.tel}}</dd>
                  </dl>
                  <div class="addr-opration addr-del">
                    <!-- 删除地址 -->
                    <a
                      href="javascript:;"
                      class="addr-del-btn"
                      @click="deleteAddress(item.addressId)"
                    >
                      <svg class="icon icon-del">
                        <use xlink:href="#icon-del" />
                      </svg>
                    </a>
                  </div>
                  <div class="addr-opration">
                    <a
                      href="javascript:;"
                      class="addr-set-default-btn"
                      v-if="!item.isDefault"
                      @click="setDefault(item.addressId)"
                    >
                      <i>设为默认</i>
                    </a>
                  </div>
                  <div class="addr-opration addr-default" v-if="item.isDefault">默认地址</div>
                </li>

                <li class="addr-new">
                  <div class="add-new-inner">
                    <i class="icon-add">
                      <svg class="icon icon-add">
                        <use xlink:href="#icon-add" />
                      </svg>
                    </i>
                    <p>添加地址</p>
                  </div>
                </li>
              </ul>
            </div>

            <div class="shipping-addr-more">
              <a
                class="addr-more-btn up-down-btn"
                :class="{'open' : limit > 3}"
                href="javascript:;"
                @click="showMore"
              >
                查看更多
                <i class="i-up-down">
                  <i class="i-up-down-l"></i>
                  <i class="i-up-down-r"></i>
                </i>
              </a>
            </div>
          </div>

          <!-- shipping method-->
          <div class="page-title-normal checkout-title">
            <h2>
              <span>配送方式</span>
            </h2>
          </div>
          <div class="shipping-method-wrap">
            <div class="shipping-method">
              <ul>
                <li class="check">
                  <div class="name">标准配送</div>
                  <div class="price">免费配送</div>
                  <div class="shipping-tips">
                    <p>一旦发货，订单应在1-7个工作日内到达目的地</p>
                  </div>
                </li>
              </ul>
            </div>
          </div>
          <div class="next-btn-wrap">
            <a class="btn btn--m btn--red" href="#">下一步</a>
          </div>
        </div>
      </div>
      <nav-footer></nav-footer>
    </div>
  </div>
</template>

<script>
import NavHeader from "../components/Header";
import NavFooter from "../components/Footer";

export default {
  name: "addr",
  props: {},
  data() {
    return {
      addressList: [],
      limit: 3,
      checkIndex: 0
    };
  },
  computed: {
    showAddressList() {
      // start must,  end option
      let showList = this.addressList.slice(0, this.limit);
      return showList;
    }
  },
  components: {
    NavHeader,
    NavFooter
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      this.axios.get("/mock/address.json").then(response => {
        let res = response.data;
        this.addressList = res.data;
        this.addressList.forEach((item, index) => {
          if (item.isDefault) {
            this.checkIndex = index;
          }
        });
      });
    },
    showMore() {
      if (this.showAddressList.length == 3) {
        this.limit = this.addressList.length;
      } else {
        this.limit = 3;
      }
    },
    setDefault(addressId) {
      this.addressList.forEach(item => {
        if (item.addressId == addressId) {
          item.isDefault = true;
        } else {
          item.isDefault = false;
        }
      });
    },
    deleteAddress(addressId) {
      this.addressList.forEach((item, index) => {
        if (item.addressId == addressId) {
          this.addressList.splice(index, 1);
        }
      });
    },
    clickState(index) {
      this.checkIndex = index;
    }
  }
};
</script>