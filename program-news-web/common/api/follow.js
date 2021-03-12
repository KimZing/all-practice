import request from '../../utils/http.js'
import {host} from '@/common/constants.js'

// 分页查询用户喜欢的文章
export const getUserLikeArticleList = (data) => {
	// pageSize pageNum userId
	return request({
		url: host + "/article/like",
		method: "GET",
		data: {
			...data,
			userId: 1
		}
	})
}