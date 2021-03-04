<template>
	<view class="home">
		<navbar></navbar>
		<tab :list="labels" @clickTab="clickTab" :current="current"></tab>
		<swiper-list :tab="labels" :current="current" @changeSwiper="changeSwiper"></swiper-list>
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
			this.$api.getAllLabel()
				.then((data) => this.labels = data)
				.catch((err) => {
					console.log(err);
				})
		},
		methods: {
			clickTab(data) {
				this.current = data.index
			},
			changeSwiper(data) {
				this.current = data.current
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
	}
</style>
