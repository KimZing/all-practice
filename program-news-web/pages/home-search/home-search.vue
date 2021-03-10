<template>
	<view class="home">
		<navbar source="search" @confirmSearch="confirmSearch"></navbar>
		<view v-if="isHistory" class="history">
			<view class="history-header">
				<view class="history-title">搜索历史</view>
				<view class="history-clear" @click="clearHistoryList">清空</view>
			</view>
			<view v-if="historyList.length > 0" class="history-footer">
				<view v-for="item, index in historyList" :key="index" class="history-item">{{item}}</view>
			</view>
			<view v-else class="no-history">
				<view> 没有搜索历史</view>
			</view>
		</view>
		<view v-else>
			搜索列表
		</view>
	</view>
</template>

<script>
	import {mapState} from 'vuex'
	export default {
		data() {
			return {
				isHistory: false
			};
		},
		computed: {
			...mapState(["historyList"])
		},
		methods: {
			confirmSearch(searchText) {
				console.log(searchText);
				// 添加历史搜索
				this.$store.dispatch("addHistory", searchText)
			},
			clearHistoryList() {
				this.$store.dispatch('clearHistoryList')
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
