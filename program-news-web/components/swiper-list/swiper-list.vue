<template>
	<view class="swiper-page">
		<swiper class="swiper" @change="changeSwiper" :current="current">
			<swiper-item v-for="(item, index) in tab" :key="index" class="swiper-item">
				<listItem :list="cacheList[index]"></listItem>
			</swiper-item>
		</swiper>
	</view>
</template>

<script>
	// MARK 千万不能和自有组件重名
	import listItem from './list-item.vue'
	export default {
		// 不能用箭头函数
		// 不使用create来做数据初始化，是因为无法拿到tab的值，所以在watch中做初始化数据的事情
		// created() {
		// 	this.getArticleList()
		// },
		watch: {
			"tab": function(newVal) {
				if(newVal.length === 0) return
				this.getArticleList(0)
			}
		},
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
				// 直接赋值到当前集合，会导致每次页面刷新都会卡顿，可以使用缓存数据(一个map<String, List>)的机制来缓存之前的数据
				list: [],
				cacheList: {},
			};
		},
		methods: {
			changeSwiper(data) {
				const current = data.detail.current
				this.$emit('changeSwiper', {
					current
				})
				// 请求当前标签下的数据
				this.getArticleList(current)
			},
			getArticleList(current) {
				// 处理全部标签
				let labelName = ""
				if(current !== 0) {
					labelName = this.tab[current].name
				}
				
				if(this.cacheList[current] && this.cacheList[current].length > 0) {return}
				
				this.$api.getArticleList(labelName).then(res => {
					let listTemp = res.list.map(l => {
						l.images = l.images.split(",");
						l.classify = l.classify.split(",")
						return l;
					})
					// 这里需要使用$set触发更新，不然更新完list，页面不会刷新
					this.$set(this.cacheList, current, listTemp)
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
