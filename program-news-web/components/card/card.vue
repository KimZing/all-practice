<template>
	<view class="card">
		<view class="card-image">
			<image src="../../static/home-select.png" mode="aspectFill"></image>
		</view>
		<view class="card-content">
			<view class="content-title">标题</view>
			<view class="content-label">
				<view class="label-item" v-for="label, index in 3" :key="index">标签{{label}}</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
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
		width: 60px;
		height: 60px;
		border-radius: 5px;
		overflow: hidden;
		image {
			width: 100%;
			height: 100%;
		}
	}
}
</style>
