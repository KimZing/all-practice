<template>
	<view class="swiper-page">
		<swiper class="swiper" @change="changeSwiper" :current="current">
			<swiper-item v-for="(item, index) in tab" :key="index" class="swiper-item">
				<listItem></listItem>
			</swiper-item>
		</swiper>
	</view>
</template>

<script>
	// MARK 千万不能和自有组件重名
	import listItem from './list-item.vue'
	export default {
		components: {
			listItem
		},
		props: {
			tab: {
				type: Array,
				default: () => {
					return [];
				}
			},
			current: {
				type: Number,
				default: 0,
			}
		},
		data() {
			return {

			};
		},
		methods: {
			changeSwiper(data) {
				const current = data.detail.current
				this.$emit('changeSwiper', {
					current
				})
			}
		}
	}
</script>

<style lang="scss">
	.swiper-page {
		height: 100%;
	}

	.swiper {
		height: 100%;
		.swiper-item {
			height: 100%;
		}
	}
</style>
