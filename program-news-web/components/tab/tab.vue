<template>
	<view class="tab">
		<scroll-view scroll-x class="tab-scroll">
			<view class="scroll-content">
				<view class="scroll-item" :class="{active: activeIndex === index}" v-for="item,index in list" :key="index" @click="handleClick(item, index)">{{item.name}}</view>
			</view>
		</scroll-view>
		<view class="tab-icons" >
			<uni-icons type="gear" size="26" color="#666666"></uni-icons>
		</view>
	</view>
</template>

<script>
	export default {
		props: {
			list: {
				type: Array,
				default () {
					return []
				},
			}
		},
		data() {
			return {
				activeIndex: 0
			};
		},
		methods: {
			handleClick: function(item, index) {
				this.activeIndex = index
				this.$emit("clickTab", {
					item,
					index
				})
			}
		}
	}
</script>

<style lang="scss">
	.tab {
		display: flex;
		flex-flow: row nowrap;
		width: 100%;
		border-bottom: 1px #f5f5f5 solid;
		background-color: #FFFFFF;
		box-sizing: border-box;

		.tab-scroll {
			flex: 1;
			overflow: hidden;
			// 特别注意，父元素的宽度问题
			// width: 0;
			box-sizing: border-box;

			.scroll-content {
				display: flex;
				align-items: center;
				flex-wrap: nowrap;
				height: 45px;
				box-sizing: border-box;

				.scroll-item {
					flex-shrink: 0;
					padding: 0 10px;
					font-size: 14px;
					color: #333333;

					&.active {
						color: $main-color;
					}
				}

			}
		}

		.tab-icons {
			position: relative;
			display: flex;
			justify-content: center;
			align-items: center;
			width: 45px;

			&::after {
				content: '';
				position: absolute;
				top: 12px;
				bottom: 12px;
				left: 0;
				width: 1px;
				background-color: #ddd;
			}
		}
	}
</style>
