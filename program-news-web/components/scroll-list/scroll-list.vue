<template>
	<view>
		<scroll-view  class="scroll-view" scroll-y>
			<view>
				<slot></slot>
			</view>
		</scroll-view>
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
.scroll-view {
	height: 100%;
	overflow: hidden;
}
</style>
