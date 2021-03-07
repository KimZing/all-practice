<template>
	<view class="icons" @click.stop="clickLike">
		<uni-icons size="20" color="#F03799" :type="like?'heart-filled':'heart'"></uni-icons>
	</view>
</template>

<script>
	export default {
		name: "like",
		data() {
			return {
				like: false
			};
		},
		methods: {
			clickLike() {
				console.log("点击收藏");
				this.like = !this.like
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
		border: 1px red solid;
	}
</style>
