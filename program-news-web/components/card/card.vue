<template>
	<view @click="openDetail">
		<view v-if="mode === 'basic'" class="card">
			<view class="card-image">
				<image class="image" :src="item.images[0]" mode="aspectFill"></image>
			</view>
			<view class="card-content">
				<view class="content-title">
					<text class="content-title-text">{{item.title}}</text>
				</view>
				<like :item="item"></like>
				<view class="content-footer">
					<view class="content-label">
						<view class="label-item" v-for="(label, index) in item.classify" :key="index">{{label}}</view>
					</view>
					<view class="content-read">
						<view class="content-read-number">{{item.readCount}}浏览</view>
					</view>
				</view>
			</view>
		</view>
		<view v-if="mode === 'multi'" class="card">
			<view class="card-content multi-card-content">
				<view class="content-title">
					<text class="content-title-text">{{item.title}}</text>
				</view>
				<like :item="item"></like>
				<view class="content-image">
					<view class="card-image" v-for="(image,index) in item.images" :key="index">
						<image class="image" :src="image" mode="aspectFill"></image>
					</view>
				</view>
				<view class="content-footer">
					<view class="content-label">
						<view class="label-item" v-for="(label, index) in item.classify" :key="index">{{label}}</view>
					</view>
					<view class="content-read">
						<view class="content-read-number">{{item.readCount}}浏览</view>
					</view>
				</view>
			</view>
		</view>
		<view v-if="mode === 'main'" class="card">
			<view class="card-content main-card-content">
				<view class="content-title">
					<text class="content-title-text">{{item.title}}</text>
				</view>
				<like :item="item"></like>
				<view class="main-image">
					<image class="image" :src="item.images[0]" mode="aspectFill"></image>
				</view>
				<view class="content-footer">
					<view class="content-label">
						<view class="label-item" v-for="(label, index) in item.classify" :key="index">{{label}}</view>
					</view>
					<view class="content-read">
						<view class="content-read-number">{{item.readCount}}浏览</view>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		props: {
			mode: {
				type: String,
				default: 'basic',
				required: false,
			},
			item: {
				type: Object,
				default () {
					return {}
				}
			}
		},
		data() {
			return {

			};
		},
		methods: {
			openDetail() {
				console.log("打开详情");
			}
		}
	}
</script>

<style lang="scss">
	.card {
		display: flex;
		padding: 10px;
		margin: 10px;
		border-radius: 5px;
		box-shadow: 0 0 5px 1px rgba($color: #000000, $alpha:0.1);
		box-sizing: border-box;

		.card-image {
			flex-shrink: 0;
			display: flex;
			align-items: center;
			width: 60px;
			height: 60px;
			border-radius: 5px;
			overflow: hidden;
		}

		.card-content {
			position: relative;
			display: flex;
			flex-direction: column;
			width: 100%;
			justify-content: space-between;
			margin-left: 10px;

			.content-title {
				padding-right: 30px;
				font-size: 14px;
				color: #333;
				font-weight: 400;
				line-height: 1.2;

				.content-title-text {
					overflow: hidden;
					width: 100%;
					display: -webkit-box;
					-webkit-box-orient: vertical;
					-webkit-line-clamp: 2;
					-ms-text-overflow: ellipsis;
					margin-bottom: 10px;
				}
			}

			.content-footer {
				display: flex;
				justify-content: space-between;

				.content-label {
					display: flex;
					align-items: center;

					.label-item {
						flex-shrink: 0;
						margin-right: 5px;
						padding: 0 5px;
						font-size: 14px;
						border: 1px $main-color solid;
						border-radius: 5px;
						color: $main-color;
					}
				}

				.content-read {
					display: flex;
					justify-content: space-between;
					align-items: center;

					.content-read-number {
						flex-shrink: 0;
						color: #999999;
						margin-left: 5px;
					}
				}
			}
		}

		.content-image {
			display: flex;
			margin-bottom: 10px;
			overflow: hidden;

			.card-image {
				margin-right: 10px;
			}
		}

		.main-image {
			flex-shrink: 0;
			width: 100%;
			height: 80px;
			margin-bottom: 10px;
			border-radius: 5px;
			overflow: hidden;
			box-sizing: border-box;
		}

		.main-card-content {
			margin-left: 0;
		}

		.multi-card-content {
			margin-left: 0;
		}
	}
	
	.image {
		width: 100%;
		height: 100%;
	}
</style>
