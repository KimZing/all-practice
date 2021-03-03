import request from '../../utils/http.js'

export const getAllLabel = (data) => {
	return request({
		url: "http://localhost:8081/label/all",
		data,
	})
}

export const getArticle = () => {
	return request({
		url: "http://localhost:8081/article/list"
	})
}