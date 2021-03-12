<template>
	<view class="mine">
		<view class="header">
			<view class="header-background">
				<image class="image" src="../../../static/avatar.png" mode="aspectFill"></image>
			</view>
			<view class="header-author">
				<view class="author-image">
					<image class="image" src="/static/avatar.png" mode="aspectFill"></image>
				</view>
				<text class="author-name">KimZing</text>
			</view>
			<view class="header-info">
				<view class="info-number">
					<text class="info-title">关注</text>
					<text>1234</text>
				</view>
				<view class="info-number">
					<text class="info-title">粉丝</text>
					<text>1234</text>
				</view>
				<view class="info-number">
					<text class="info-title">积分</text>
					<text>1234</text>
				</view>
			</view>
		</view>
		<view class="content">
			<view class="content-item">
				<view class="item-title">
					<uni-icons type="contact" size="18" color="#666"></uni-icons>
					<text class="title">我的文章</text>
				</view>
				<uni-icons type="arrowright" size="14" color="#666"></uni-icons>
			</view>
			<view class="content-item">
				<view class="item-title">
					<uni-icons type="help" size="18" color="#666"></uni-icons>
					<text class="title">意见反馈</text>
				</view>
				<uni-icons type="arrowright" size="14" color="#666"></uni-icons>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {

			}
		},
		methods: {

		}
	}
</script>

<style lang="scss">
	page {
		background-color: #f5f5f5;
	}

	.mine {
		display: flex;
		flex-direction: column;

		.header {
			position: relative;
			display: flex;
			padding-bottom: 15px;
			flex-direction: column;

			.header-background {
				position: absolute;
				top: 0;
				bottom: 0;
				right: 0;
				left: 0;
				filter: blur(8px);
				opacity: 0.3;

				.image {
					width: 100%;
					height: 100%;
				}
			}

			.header-author {
				display: flex;
				flex-direction: column;
				justify-content: center;
				align-items: center;
				padding-top: 30px;

				.author-image {
					flex-shrink: 0;
					width: 80px;
					height: 80px;
					border-radius: 50%;
					overflow: hidden;

					.image {
						width: 100%;
						height: 100%;
					}
				}

				.author-name {
					margin-top: 10px;
					font-size: 16px;
					font-weight: bold;
				}
			}

			.header-info {
				display: flex;
				justify-content: space-around;
				margin-top: 30px;

				.info-number {
					display: flex;
					justify-content: center;
					align-items: center;
					flex-direction: column;
					width: 100%;
					font-size: 14px;
					color: #7f7f7f;

					.info-title {
						font-size: 14px;
						color: #333;
					}
				}
			}
		}

		.content {
			display: flex;
			flex-direction: column;

			.content-item {
				display: flex;
				justify-content: space-between;
				align-items: center;
				padding: 15px;
				margin-bottom: 10px;
				background-color: #fff;
				color: #333;
				font-size: 16px;

				.item-title {
					display: flex;
					align-items: center;

					.title {
						margin-left: 5px;
					}
				}
			}
		}
	}
</style>
