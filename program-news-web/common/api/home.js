import request from '../../utils/http.js'

export const getAllLabel = (data) => {
	return request({
		url: "http://localhost:8081/label/all",
		data,
	})
}

export const getArticleList = (label) => {
	return request({
		url: "http://localhost:8081/article/list",
		method: "POST",
		data: {classify: label}
	})
}