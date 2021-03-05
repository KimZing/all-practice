<template>
	<list-scroll>
		<card :mode="item.mode" v-for="item in list" :key="item.id" :item="item"></card>
		<uni-load-more status="noMore"></uni-load-more>
	</list-scroll>
</template>

<script>
	export default {
		props: {
			list: {
				type: Array,
				default() {
					return []
				}
			}
		}
	}
</script>

<style>
</style>
