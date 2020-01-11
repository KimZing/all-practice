//使用 `godoc -http :8088` 来查看文档，注意的是不能是main包
package __godoc

// 计算两个数的和
//     eg: 1 + 2
func Add(a, b int) int {
	return a + b
}

/*
计算a*b
*/
func Mul(a, b int) int {
	return a * b
}
