import request from '../../utils/http.js'

const getAllLabel = (data) => {
	return request({
		url: "http://localhost:8081/label/all",
		data,
	})
}

export default {getAllLabel};
