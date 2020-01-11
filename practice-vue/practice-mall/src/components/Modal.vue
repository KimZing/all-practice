<template>
  <div>
    <div class="md-modal modal-msg md-modal-transition" :class="{'md-show': mdShow}">
      <div class="md-modal-inner">
        <div class="md-top" @click="close">
          <button class="md-close">关闭</button>
        </div>
        <div class="md-content">
          <div class="confirm-tips">
            <slot name="message"></slot>
          </div>
          <div class="btn-wrap">
            <slot name="btnGroup"></slot>
          </div>
        </div>
      </div>
    </div>
    <!-- <div :class="{'md-overlay': mdShow}"></div> -->
    <div class="md-overlay" v-if="mdShow" @click="close"></div>
  </div>
</template>

<script>
export default {
  name: "modal",
  props: ["mdShow"],
  methods: {
    close() {
      // 向外层发射close事件，外层通过v-on接收指定事件并调用对应方法处理
      this.$emit("close");
    }
  }
};
</script>

<style scoped>
</style>
