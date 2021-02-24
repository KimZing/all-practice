const request = (options) => {
	const {
		url,
		data
	} = options
	return new Promise((resolve, reject) => {
		uni.request({
			url,
			data,
			success: (res) => {
				if (res.data.code === '0') {
					resolve(res.data.data)
				} else {
					resolve(res.data.message)
				}
			},
			fail: (err) => {
				reject(err)
			}
		})
	})
}

export default request