<template>
	<view class="swiper-page">
		<swiper class="swiper" @change="changeSwiper" :current="current">
			<swiper-item v-for="(item, index) in tab" :key="index" class="swiper-item">
				<listItem :list="cacheList[index]" @loadMore="loadMore" :load="cacheLoad[index]"></listItem>
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
			tab(newVal) {
				if (newVal.length === 0) return
				this.cacheList = []
				this.cacheLoad = []
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
				// list: [],
				cacheList: [],
				cacheLoad: [],
				pageSize: 10,
			};
		},
		methods: {
			loadMore() {
				this.cacheLoad[this.current].pageNum++
				
				// 如果没有更多数据了，就不继续请求数据了
				if(this.cacheLoad[this.current].status === 'noMore') {
					return
				}
				
				this.getArticleList(this.current)
			},
			changeSwiper(data) {
				const current = data.detail.current
				this.$emit('changeSwiper', {
					current
				})
				// 如果有缓存，则不去重复请求
				if (this.cacheList[current] && this.cacheList[current].length > 0) {
					return
				}
				// 请求当前标签下的数据
				this.getArticleList(current)
			},
			getArticleList(current) {
				// 初始化当前页数
				if (!this.cacheLoad[current]) {
					this.cacheLoad[current] = {
						pageNum: 1,
						status: "loading"
					}
				}
				// 处理全部标签
				let labelName = ""
				if (current !== 0) {
					labelName = this.tab[current].name
				}

				this.$api.getArticleList({
					classify: labelName,
					pageSize: this.pageSize,
					pageNum: this.cacheLoad[this.current].pageNum
				}).then(res => {
					let listTemp = res.list.map(l => {
						l.images = l.images.split(",");
						l.classify = l.classify.split(",")
						return l;
					})
					// 如果没有更过数据了，那么修改当前的load组件状态
					if (listTemp.length === 0) {
						let oldLoad = {status: "noMore", pageNum: this.cacheLoad[current].pageNum}
						this.$set(this.cacheLoad, current, oldLoad)
						// this.$forceUpdate()
						return 
					}
					// 保留之前的数据
					let oldList = this.cacheList[current] || []
					listTemp = [...oldList, ...listTemp]
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
		.swiper {
			height: 100%;
			.swiper-item {
				height: 100%;
			}
		}
	}

	
</style>
