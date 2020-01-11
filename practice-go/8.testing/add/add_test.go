/*
测试文件名以 `_test` 结尾
测试方法以 `Test`开头

功能测试方法参数为 `t *testing.T`
压力测试方法参数为 `b *testing.B`
*/
package add

import "testing"

//功能测试 ：：表格测试
/*使用 `go test .`即可测试当前目录中的方法
使用 `go test -coverprofile=c.out`测试覆盖率,并生成文件
使用 `go tool cover -html=c.out` 通过网页查看
*/
func TestAdd(t *testing.T) {
	datas := []struct {
		a, b, c int
	}{
		{1, 2, 3},
		{1, 5, 6},
		{4, 2, 6},
		{3, 3, 6},
		{6, 2, 8},
	}
	for _, tt := range datas {
		if actual := Add(tt.a, tt.b); actual != tt.c {
			t.Errorf("got %d of Add(%d, %d), expected %d", actual, tt.a, tt.b, tt.c)
		}
	}
}

//压力测试
//使用 `go test -bench .` 来运行压测
//同样可以使用 -coverprofile参数
func BenchmarkAdd(b *testing.B) {
	datas := []struct {
		a, b, c int
	}{
		{1, 2, 3},
	}
	//重置计时器
	b.ResetTimer()
	for i := 0; i < b.N; i++ {
		if actual := Add(datas[0].a, datas[0].b); actual != datas[0].c {
			b.Errorf("got %d of Add(%d, %d), expected %d", actual, datas[0].a, datas[0].b, datas[0].c)
		}
	}
}

//性能分析
/*
输出性能数据：`go test -bench . -cpuprofile=cpu.out`
使用 `go tool pprof cpu.out` 可以查看性能数据
输入 `web` 以svg查看，图形越大，占用时间越长。  (需要先安装graphviz,  : graphviz.org)
接下来进行性能优化。
*/
