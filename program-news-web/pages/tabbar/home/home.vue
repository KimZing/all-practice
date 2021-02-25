<template>
	<view>
		<navbar></navbar>
		<tab :list="labels" @clickTab="clickTab"></tab>
		<scroll-list>
			<view v-for="(i,index) in 100" :key="index">{{i}}</view>
		</scroll-list>
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

</style>
