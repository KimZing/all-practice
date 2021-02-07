<template>
	<view>
		<view class="navbar">
			<view :style="{height: statusBarHeight +'px'}"></view>
			<view class="navbar-content" :style="{width: navbarWidth + 'px', height: navbarHeight + 'px'}">
				<view class="navbar-search">
					<view class="search-icon">
						<uni-icons type="search" size="16" color="#999"></uni-icons>
					</view>
					<view class="search-text">uni-app、vue</view>
				</view>
			</view>
		</view>
		<view :style="{height: fixHeight + 'px'}"></view>
	</view>
</template>

<script>
	export default {
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
			this.navbarHeight = (menuInfo.bottom - systemInfo.statusBarHeight) + (menuInfo.top - systemInfo.statusBarHeight)
			// #endif
		},
		computed: {
			fixHeight: function() {
				return this.statusBarHeight + this.navbarHeight
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
			
				.search-icon {
				}
			
				.search-text {
					margin-left: 10px;
					font-size: 12px;
					color: #999999;
				}
			}
		}
		
	}
</style>
