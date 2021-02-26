<template>
	<view class="home">
		<navbar></navbar>
		<tab :list="labels" @clickTab="clickTab"></tab>
		<list-scroll>
			<view v-for="(i,index) in 100" :key="index">
				{{i}}
			</view>
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
		border: 1px red solid;

	}
</style>
