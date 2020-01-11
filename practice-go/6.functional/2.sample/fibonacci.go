package main

import "fmt"

func fibonacci() func() int {
	var a, b = 0, 1
	return func() int {
		a, b = b, a+b
		return a
	}
}

func main() {
	//把函数绑定到fibo变量上，那么在fibo内部的自由变量会一直保持，而新绑定的变量则又重新开始，例如下面的fibo2
	fibo := fibonacci()
	fmt.Println(fibo())
	fmt.Println(fibo())
	fmt.Println(fibo())
	fmt.Println(fibo())

	fibo2 := fibonacci()
	fmt.Println(fibo2())
	fmt.Println(fibo2())
	fmt.Println(fibo2())
	fmt.Println(fibo2())

	//学到这，我对闭包的理解：跟随变量走，这个变量中包含了func和自由变量，自由变量的状态是跟随这个变量的。
	//而我们通常用闭包中的自由变量来保存其之前的 操作状态
}
