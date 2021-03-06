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
