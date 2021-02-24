<template>
	<view>
		<navbar></navbar>
		<tab :list="labels"></tab>
		<view v-for="(i,index) in 100" :key="index">{{i}}</view>
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
			.catch((err)=> {
				console.log(err);
			})
		},
		methods: {

		}
	}
</script>

<style lang="scss">

</style>
