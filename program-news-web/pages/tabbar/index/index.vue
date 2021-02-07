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
			uni.request({
				url: "http://localhost:8081/label/all",
				success: (res) => {
					this.labels = res.data.data
				}
			})
		},
		methods: {

		}
	}
</script>

<style lang="scss">

</style>
