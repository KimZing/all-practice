<template>
	<view class="home">
		<navbar></navbar>
		<tab :list="labels" @clickTab="clickTab"></tab>
		<list-scroll>
			<card v-for="item,index in 8" :key="index"></card>
		</list-scroll>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				labels: []
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
			clickTab: function(data) {
				console.log(data)
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
