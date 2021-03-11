<template>
	<view class="label">
		<view class="label-box">
			<view class="label-header">
				<view class="label-mine">我的标签</view>
				<view class="label-edit" @click="editTabel">{{isEdit ? '完成': '编辑'}}</view>
			</view>
			<view class="label-content">
				<view v-if="!loading" class="label-item" v-for="item,index in showLabelList" :key="item.id">
					{{item.name}}
					<uni-icons v-if="isEdit" type="clear" size="18" color="#ff0612" class="label-remove"
						@click="removeLabel(item.id)"></uni-icons>
				</view>
				<view v-if="loading">
					<uni-load-more iconType="snow" status="loading"></uni-load-more>
				</view>
				<view v-if="showLabelList.length === 0" class="no-data">
					暂无标签
				</view>
			</view>
		</view>
		<view class="label-box">
			<view class="label-header">
				<view class="label-recommend">标签推荐</view>
			</view>
			<view class="label-content">
				<view v-if="!loading" @click="addLabel(item.id)" class="label-item" v-for="item,index in unShowLabelList" :key="item.id">
					{{item.name}}
				</view>
				<view v-if="loading">
					<uni-load-more iconType="snow" status="loading"></uni-load-more>
				</view>
				<view v-if="unShowLabelList.length === 0" class="no-data">
					暂无推荐
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				loading: true,
				labelList: [],
				isEdit: false,
			};
		},
		computed: {
			showLabelList: function() {
				return this.labelList.filter(item => item.show === 1)
			},
			unShowLabelList() {
				return this.labelList.filter(item => item.show !== 1)
			}
		},
		onLoad() {
			this.loading = true
			this.$api.getAllLabel()
				.then((data) => {
					this.labelList = data
					this.loading = false
				})
				.catch((err) => {
					console.log(err);
				})
		},
		methods: {
			editTabel() {
				this.isEdit = !this.isEdit
				if (!this.isEdit) {
					this.$api.updateLabelList(this.labelList).then(res => {
						uni.$emit("EditLabelSuccess")
						uni.showToast({
							icon: 'none',
							title: "保存成功"
						})
					}).catch(err => {
						console.log(err);
					})
					
				}
			},
			removeLabel(labelId) {
				this.labelList = this.labelList.map(l => {
					if (l.id === labelId) {
						l.show = 0
					}
					return l
				})
			},
			addLabel(labelId) {
				if(!this.isEdit) return 
				this.labelList = this.labelList.map(l => {
					if(l.id === labelId) {
						l.show = 1
					}
					return l
				})
			}
		}
	}
</script>

<style lang="scss">
	page {
		background-color: #f5f5f5;
	}

	.label {
		.label-box {
			display: flex;
			flex-direction: column;
			background-color: #fff;
			padding: 10px 15px;
			margin-bottom: 10px;

			.label-header {
				display: flex;
				justify-content: space-between;
				padding-bottom: 10px;
				border-bottom: 1px #f3f3f3 solid;

				.label-mine {
					font-size: 14px;
					color: #666;
				}

				.label-recommend {
					font-size: 14px;
					color: #ff4a4d;
				}

				.label-edit {
					font-size: 14px;
					color: #3aa64c;
				}
			}

			.label-content {
				display: flex;
				flex-flow: row wrap;
				margin-top: 10px;

				.label-item {
					position: relative;
					color: #666;
					font-size: 14px;
					border: 1px #666 solid;
					border-radius: 5px;
					padding: 2px 10px;
					margin-right: 10px;
					margin-bottom: 10px;

					.label-remove {
						position: absolute;
						top: -10px;
						right: -7px;
					}
				}
			}
		}
	}

	.no-data {
		text-align: center;
		line-height: 50px;
		width: 100%;
		color: #999999;
		height: 50px;
	}
</style>
