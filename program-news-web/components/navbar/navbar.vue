<template>
	<view>
		<view class="navbar">
			<view :style="{height: statusBarHeight +'px'}"></view>
			<view v-if="source==='home'" class="navbar-content"
				:style="{width: navbarWidth + 'px', height: navbarHeight + 'px'}">
				<view class="navbar-search" @click="openSearch">
					<view class="search-icon">
						<uni-icons type="search" size="16" color="#999"></uni-icons>
					</view>
					<view class="search-text">uni-app、vue</view>
				</view>
			</view>

			<view v-else class="navbar-content search" :style="{width: navbarWidth + 'px', height: navbarHeight + 'px'}">
				<view class="back-icon" @click="backToHome">
					<uni-icons type="back" size="25" color="#fff"></uni-icons>
				</view>
				<view class="navbar-search">
					<input type="text" placeholder="请输入内容" maxlength="-1" @confirm="confirmSearch" focus="true"/>
				</view>
			</view>
		</view>
		<view :style="{height: fixHeight + 'px'}"></view>
	</view>
</template>

<script>
	export default {
		props: {
			source: {
				type: String,
				default () {
					return "home"
				}
			}
		},
		data() {
			return {
				statusBarHeight: 0,
				navbarWidth: 375,
				navbarHeight: 45
			};
		},
		created() {
			let systemInfo = uni.getSystemInfoSync()
			this.statusBarHeight = systemInfo.statusBarHeight
			this.navbarWidth = systemInfo.windowWidth
			// #ifndef H5 || APP-PLUS || MP-ALIPAY
			let menuInfo = uni.getMenuButtonBoundingClientRect()
			this.navbarWidth = menuInfo.left
			this.navbarHeight = (menuInfo.bottom - systemInfo.statusBarHeight) + (menuInfo.top - systemInfo
				.statusBarHeight)
			// #endif
			
		},
		computed: {
			fixHeight: function() {
				return this.statusBarHeight + this.navbarHeight
			}
		},
		methods: {
			openSearch() {
				uni.navigateTo({
					url: "/pages/home-search/home-search"
				})
			},
			confirmSearch(e) {
				this.$emit("confirmSearch", e.detail.value)
			},
			backToHome() {
				uni.navigateBack()
			}
		}
	}
</script>

<style lang="scss">
	.navbar {
		position: fixed;
		top: 0;
		left: 0;
		background-color: $main-color;
		width: 100%;
		z-index: 99;

		.navbar-content {
			display: flex;
			justify-content: center;
			align-items: center;
			height: 45px;
			padding: 0 15px;
			box-sizing: border-box;

			.navbar-search {
				display: flex;
				align-items: center;
				height: 30px;
				width: 100%;
				border-radius: 30px;
				padding: 0 10px;
				background-color: #FFFFFF;

				.search-icon {}

				.search-text {
					margin-left: 10px;
					font-size: 12px;
					color: #999999;
				}
			}
			&.search {
				padding-left: 0;
				.navbar-search {
					border-radius: 5px;
					input {
						width: 100%;
					}
				}
				.back-icon {
					display: flex;
					justify-content: center;
					align-items: center;
					width: 30px;
					height: 30px;
					padding: 0 5px;
				}
			}
		}

	}
</style>
