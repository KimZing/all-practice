// 导出所有的api
const requireApi = require.context(
	// 搜索的目录
	'.',
	// 是否搜索子目录
	false,
	// 搜索的文件
	/.js$/
)

let modules = {}

requireApi.keys().forEach(key => {
	if(key === './index.js') return
	Object.assign(modules, requireApi(key))
})

export default modules