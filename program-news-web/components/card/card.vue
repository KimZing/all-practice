<template>
	<view>
		<view v-if="mode === 'basic'" class="card">
			<view class="card-image">
				<image :src="item.images[0]" mode="aspectFill"></image>
			</view>
			<view class="card-content">
				<view class="content-title">
					<text>{{item.title}}</text>
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
		<view v-if="mode === 'multi'" class="card">
			<view class="card-content">
				<view class="content-title">
					<text>{{item.title}}</text>
				</view>
				<view class="content-image">
					<view class="card-image" v-for="(image,index) in item.images" :key="index">
						<image :src="image" mode="aspectFill"></image>
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
			<view class="card-content">
				<view class="content-title">
					<text>{{item.title}}</text>
				</view>
					<view class="main-image">
						<image :src="item.images[0]" mode="aspectFill"></image>
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
				default() {
					return {}
				}
			}
		},
		data() {
			return {

			};
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

			image {
				width: 100%;
				height: 100%;
			}
		}

		.card-content {
			display: flex;
			flex-direction: column;
			width: 100%;
			justify-content: space-between;
			margin-left: 10px;

			text {
				width: 100%;
				overflow: hidden;
				display: -webkit-box;
				-webkit-box-orient: vertical;
				-webkit-line-clamp: 2;
				-ms-text-overflow: ellipsis;
				margin-bottom: 10px;
				font-size: 14px;
				font-weight: 400;
				color: #333;
				line-height: 1.2;
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
			height: 120px;
			margin-bottom: 10px;
			border-radius: 5px;
			overflow: hidden;
			box-sizing: border-box;
			image {
				width: 100%;
				height: 100%;
			}
		}
	}
</style>
