<template>
	<view class="home">
		<navbar source="search" @confirmSearch="confirmSearch"></navbar>
		<view v-if="isHistory" class="history">
			<view class="history-header">
				<view class="history-title">搜索历史</view>
				<view class="history-clear" @click="clearHistoryList">清空</view>
			</view>
			<view v-if="historyList.length > 0" class="history-footer">
				<view v-for="item, index in historyList" :key="index" class="history-item" @click="clickHistory(item)">{{item}}</view>
			</view>
			<view v-else class="no-history">
				<view> 没有搜索历史</view>
			</view>
		</view>
		<listItem v-else :list="articleList" @loadMore="loadMore" :load="load"></listItem>
	</view>
</template>

<script>
	import {
		mapState
	} from 'vuex'
	import listItem from '../../components/swiper-list/list-item.vue'
	export default {
		components: {listItem},
		data() {
			return {
				isHistory: true,
				articleList: [],
				load: {
					status: "loading",
				},
				pageNum: 1,
				pageSize: 10,
				search: ""
			};
		},
		computed: {
			...mapState(["historyList"])
		},
		methods: {
			confirmSearch(search) {
				// 添加历史搜索
				this.$store.dispatch("addHistory", search)
				this.search = search
				this.pageNum = 1
				this.articleList = [],
				this.load.status = "loading"
				this.searchArticle(this.search)
			},
			clearHistoryList() {
				this.$store.dispatch('clearHistoryList')
				uni.showToast({
					title: "清空成功"
				})
			},
			loadMore() {
				console.log("asdfasdf");
				this.pageNum++
				// 如果没有更多数据了，就不继续请求数据了
				if (this.load.status === 'noMore') {
					return
				}
				this.searchArticle(this.search)
			},
			searchArticle(search) {
				this.isHistory = false;
				this.$api.searchArticle({
					search,
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
			},
			clickHistory(item) {
				this.search = item
				this.confirmSearch(item)
			}
		}

	}
</script>

<style lang="scss">
	page {
		height: 100%;
		display: flex;
		background-color: #f5f5f5;
	}

	.home {
		display: flex;
		flex: 1;
		flex-direction: column;

		.history {
			background-color: #fff;
			flex-direction: column;

			.history-header {
				display: flex;
				flex-flow: row nowrap;
				justify-content: space-between;
				align-items: center;
				padding: 10px 15px;
				font-size: 14px;
				border-bottom: 1px #f5f5f5 solid;

				.history-title {
					color: $main-color;
				}

				.history-clear {
					color: #30b33a;
					font-weight: bold;
				}
			}

			.history-footer {
				display: flex;
				flex-flow: row wrap;
				padding: 15px;
				padding-top: 0;

				.history-item {
					padding: 2px 10px;
					margin-top: 12px;
					margin-right: 10px;
					border-radius: 5px;
					border: 1px #666 solid;
					font-size: 14px;
					color: #666;
				}
			}

			.no-history {
				width: 100%;
				height: 200px;
				font-size: 12px;
				color: #999;
				display: flex;
				justify-content: center;
				align-items: center;
			}
		}
	}
</style>
