<template>
	<view class="home">
		<navbar></navbar>
		<tab :list="labels" @clickTab="clickTab" :current="current"></tab>
		<swiper-list class="swiper-list" :tab="labels" :current="current" @changeSwiper="changeSwiper"></swiper-list>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				labels: [],
				current: 0
			}
		},
		onLoad() {
			// 监听事件，只有打开的页面才能监听到
			uni.$on("EditLabelSuccess", () => {
				this.getAllLabel()
				this.current = 0
			})
			this.getAllLabel()
		},
		methods: {
			clickTab(data) {
				this.current = data.index
			},
			changeSwiper(data) {
				this.current = data.current
			},
			getAllLabel() {
				this.$api.getAllLabel()
					.then((data) => {
						data.unshift({name: "全部", show: 1})
						// 只展示设置show=1的标签
						this.labels = data.filter(i => i.show === 1)
					})
					.catch((err) => {
						console.log(err);
					})
			}
		}
	}
</script>

<style lang="scss">
	page {
		height: 100%;
		display: flex;
	}

	.home {
		display: flex;
		flex-direction: column;
		flex: 1;
		// 这个地方也同时解决了tab的溢出问题
		overflow: hidden;
		// MARK 这个地方真的是坑死人，h5是正常的，但是小程序里就必须加上，否则占据的高度为0
		.swiper-list {
			flex: 1;
		}
	}
</style>
