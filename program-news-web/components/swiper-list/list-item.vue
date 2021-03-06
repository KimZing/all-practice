<template>
	<list-scroll @loadMore="loadMore">
		<card :mode="item.mode" v-for="item in list" :key="item.id" :item="item"></card>
		<uni-load-more  v-if="list.length === 0 || list.length > 7" iconType="snow" :status="load.status"></uni-load-more>
	</list-scroll>
</template>

<script>
	export default {
		props: {
			list: {
				type: Array,
				default () {
					return []
				}
			},
			load: {
				type: Object,
				default() {
					return {
						status: "loading"
					}
				}
			}
		},
		methods: {
			loadMore() {
				this.$emit("loadMore")
			}
		}
	}
</script>

<style>
</style>
