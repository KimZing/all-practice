package main

import (
	"fmt"
	"math"
	"reflect"
	"runtime"
)

//定义函数, 单个返回值, 返回值写在最后
func caculate(a, b int, operation string) int {
	switch operation {
	case "+":
		return a + b
	case "-":
		return a - b
	case "*":
		return a * b
	case "/":
		return a / b
	default:
		panic("operation is not support!")
	}
}

//多个返回值,返回值无名
func div(a, b int) (int, int) {
	return a / b, a % b
}

//起名后，可以方便调用者查看
func div2(a, b int) (p, r int) {
	//第一种方式，仅用于非常简单的函数。逻辑代码多时，容易乱，不推荐使用
	p = a / b
	r = a % b
	return
	//第二种方式，名字可以不用
	return a / b, a % b
}

//多返回值的常用场景：返回错误信息
func caculate2(a, b int, operation string) (result int, err error) {
	switch operation {
	case "+":
		return a + b, nil
	case "-":
		return a - b, nil
	case "*":
		return a * b, nil
	case "/":
		return a / b, nil
	default:
		return 0, fmt.Errorf("error opertion with %s", operation)
	}
}

//函数的参数也可以是一个函数，函数是一等公民，函数式编程,可以自己决定
func apply(op func(a, b int) int, a, b int) int {
	p := reflect.ValueOf(op).Pointer()
	funcName := runtime.FuncForPC(p).Name()
	fmt.Printf("func name is %s \n", funcName)

	return op(a, b)
}

//函数也可以定义为一种类型
type oper func(a, b int) int

//可以将上面的方法改为如下
func apply2(op oper, a, b int) int {
	//...
	return 0
}

//可变参数
func sum(numbers ...int) (sum int) {
	//一个参数是index, 两个参数会返回 index,value
	for i := range numbers {
		sum += numbers[i]
	}
	//for _, value := range numbers {
	//	sum += value
	//}

	return sum
}

func main() {
	fmt.Println(caculate(12, 6, "/"))

	fmt.Println(div(10, 3))

	p, r := div2(4, 3)
	fmt.Println(p, r)

	//只想接收一个返回值可以使用_进行忽略
	p, _ = div2(5, 2)
	fmt.Println(p)

	if result, err := caculate2(12, 10, "x"); err != nil {
		fmt.Println(err)
	} else {
		fmt.Println(result)
	}

	//方式一：匿名函数的方式
	fmt.Println(apply(
		func(a, b int) int {
			return int(math.Pow(float64(a), float64(b)))
		}, 10, 2), //写个逗号就不会报语法错误了
	)
	//方式二：定义变量的方式
	method := func(a, b int) int {
		return int(math.Pow(float64(a), float64(b)))
	}
	fmt.Println(apply(method, 11, 12))
	//方式三：使用额外定义的函数
	fmt.Println(apply(method2, 13, 14))
	//方式四
	method3 := method2
	fmt.Println(apply(method3, 1, 2))
	//一句话总结：函数是一等公民的意思就是可以当成一个变量

	fmt.Println(sum(1, 2, 3))
}

func method2(a, b int) int {
	return int(math.Pow(float64(a), float64(b)))
}
