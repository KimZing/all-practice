<template>
	<view class="icons" @click.stop="clickLike">
		<uni-icons size="20" color="#F03737" :type="like?'heart-filled':'heart'"></uni-icons>
	</view>
</template>

<script>
	export default {
		name: "like",
		props: {
			item: {
				type: Object,
				default () {
					return {}
				}
			}
		},
		created() {
			this.like = this.item.isLike
		},
		data() {
			return {
				like: false
			};
		},
		methods: {
			clickLike() {
				this.like = !this.like
				uni.showLoading()
				this.$api.updateArticleLike(this.item.id).then(res => {
					uni.hideLoading()
					uni.showToast({
						title: this.like? "收藏成功" : "取消收藏",
						icon: 'none'
					})
					// 触发更新时间
					uni.$emit("updateArticle")
				}).catch(e => {
					uni.hideLoading()
				})
			}
		}
	}
</script>

<style>
	.icons {
		display: flex;
		justify-content: center;
		align-items: center;
		position: absolute;
		top: 0;
		right: 0;
		width: 20px;
		height: 20px;
	}
</style>
