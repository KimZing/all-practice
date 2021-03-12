import request from '../../utils/http.js'

const host = "http://8080.kimzing.com"
// const host = "http://localhost:8080"

// 获取所有标签
export const getAllLabel = (data) => {
	return request({
		url: host + "/label/all",
		data,
	})
}

// 分页获取文章列表
export const getArticleList = (data) => {
	const {
		classify,
		pageNum,
		pageSize
	} = data;
	return request({
		url: host + "/article/list?pageNum=" + pageNum + "&pageSize=" + pageSize,
		method: "POST",
		data: {
			userId: 1,
			classify
		}
	})
}

// 根据关键词搜索文章
export const searchArticle = (data) => {
	// pageSize pageNum search userId
	return request({
		url: host + "/article/search",
		method: "GET",
		data: {
			...data,
			userId: 1
		}
	})
}

// 批量更新标签信息
export const updateLabelList = (labelList) => {
	return request({
		url: host + "/label/list",
		method: "PUT",
		data: labelList
	})
}


// 更新当前用户的文章收藏状态
export const updateArticleLike = (articleId) => {
	return request({
		url: host + "/article/like",
		method: "PUT",
		data: {
			articleId,
			userId: 1
		}
	})
}
