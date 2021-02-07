<template>
	<view>
		<navbar></navbar>
		<tab></tab>
		<view v-for="(i,index) in 100" :key="index">{{i}}</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
			}
		},
		onLoad() {
			
		},
		methods: {

		}
	}
</script>

<style lang="scss">
	
</style>
