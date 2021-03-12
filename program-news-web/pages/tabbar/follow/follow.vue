<template>
	<view class="follow">
		<view class="header">
			<view class="header-tab">
				<view class="tab-item" :class="{active: index === 0 ? true:false}" @click="clickTab(0)">
					文章
				</view>
				<view class="tab-item" :class="{active: index === 1 ? true:false}" @click="clickTab(1)">
					作者
				</view>
			</view>
		</view>
		<view class="content">
			<swiper class="swiper" :current="index">
				<swiper-item class="swiper-item">
					<view class="item-wrapper">
						<listItem :list="articleList" @loadMore="loadMore" :load="load">
						</listItem>
					</view>
				</swiper-item>
				<swiper-item class="swiper-item">
					作者
				</swiper-item>
			</swiper>
		</view>
	</view>
</template>

<script>
	import listItem from '@/components/swiper-list/list-item.vue'
	export default {
		components: {
			listItem
		},
		data() {
			return {
				index: 0,
				articleList: [],
				load: {
					status: "loading"
				},
				pageNum: 1,
				pageSize: 10,
			}
		},
		onLoad() {
			uni.$on("updateArticle", () =>{
				this.articleList = [];
				this.pageNum = 1;
				this.getUserLikeArticleList()
			})
			this.getUserLikeArticleList()
		},
		methods: {
			clickTab(index) {
				this.index = index
			},
			loadMore() {
				this.pageNum++
				// 如果没有更多数据了，就不继续请求数据了
				if (this.load.status === 'noMore') {
					return
				}
				this.getUserLikeArticleList()
			},
			getUserLikeArticleList() {
				this.$api.getUserLikeArticleList({
					pageSize: 10,
					pageNum: this.pageNum
				}).then(res => {
					let listTemp = res.list.map(l => {
						l.images = l.images.split(",");
						l.classify = l.classify.split(",")
						return l;
					})
					// 如果没有更过数据了，那么修改当前的load组件状态
					if (listTemp.length === 0) {
						this.load.status = "noMore"
						return
					}

					// 保留之前的数据
					listTemp = [...this.articleList, ...listTemp]
					// 这里需要使用$set触发更新，不然更新完list，页面不会刷新
					this.articleList = listTemp
				})
			}
		}
	}
</script>

<style lang="scss">
	page {
		height: 100%;
		display: flex;
	}

	.follow {
		height: 100%;
		display: flex;
		flex-direction: column;
		flex: 1;
		box-sizing: border-box;

		.header {
			height: 30px;
			padding: 10px 20px;

			.header-tab {
				display: flex;
				justify-content: center;
				align-items: center;
				width: 100%;
				height: 100%;
				border: 1px $main-color solid;
				border-radius: 5px;
				border-bottom: 2px #108ac7 solid;

				.tab-item {
					display: flex;
					justify-content: center;
					align-items: center;
					font-size: 14px;
					width: 100%;
					height: 100%;
					color: #666;

					&:first-child {
						border-right: 1px $main-color solid;
					}
				}

				.active {
					color: $main-color
				}

			}
		}

		.content {
			height: 100%;

			.swiper {
				height: 100%;

				.swiper-item {
					.item-wrapper {
						height: 100%;
					}
				}
			}
		}
	}
</style>
