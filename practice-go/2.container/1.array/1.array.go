package main

import "fmt"

//数组的声明
func initArray() {
	//声明不赋值
	var arr1 [5]int
	fmt.Println(arr1)
	//声明并赋值
	arr2 := [4]int{2, 4, 4}
	fmt.Println(arr2)
	//事先不确定数组长度
	arr3 := [...]int{2, 9, 6, 23, 3}
	fmt.Println(arr3)
	//二维数组
	arr4 := [...][4]int{{2, 4, 5, 3}, {12, 23}}
	fmt.Println(arr4)
}

//数组是值类型，如果只能传入[5]int类型,传入[4]int会提示类型不对
//由于长度也是数组类型的一部分,因此[5]int与[4]int是不同的类型，数组也就不能改变长度
func printArray(arr [5]int) {
	for i := range arr {
		fmt.Println(i)
	}
	for i, v := range arr {
		fmt.Println(i, v)
	}
}

//数组是值传递，会对传入的值进行拷贝，无法修改入参的值。如果想要修改，可以传入指针，但是一般我们不这么用，会使用切片
// 切片的传入和指针类似，不过会多一些信息
func modifyArray(arr [5]int) {
	arr[0] = 1
	fmt.Println(arr)
}

func main() {
	initArray()

	var arr1 [5]int
	printArray(arr1)
	//var arr2 [4]int
	//printArray(arr2)

	modifyArray(arr1)
	//arr1的值并没有改变
	printArray(arr1)
}
