import request from '../../utils/http.js'

export const getAllLabel = (data) => {
	return request({
		url: "http://localhost:8081/label/all",
		data,
	})
}

export const getArticleList = (data) => {
	const {
		classify,
		pageNum,
		pageSize
	} = data;
	return request({
		url: "http://localhost:8081/article/list?pageNum=" + pageNum + "&pageSize=" + pageSize,
		method: "POST",
		data: {
			classify
		}
	})
}

export const searchArticle = (data) => {
	// pageSize pageNum search userId
	return request({
		url: "http://localhost:8081/article/search",
		method: "GET",
		data
	})
}



export const updateArticleLike = (articleId) => {
	return request({
		url: "http://localhost:8081/article/like",
		method: "PUT",
		data: {
			articleId
		}
	})
}
