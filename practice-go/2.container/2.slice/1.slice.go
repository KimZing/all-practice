//slice是原始数组的视图，通过ptr,len,cap三个要素进行关联
package main

import "fmt"

//切片的创建
func InitSlice() {
	//从数组创建,可以从指定范围创建,包首不包尾
	arr := [10]int{1, 3, 5}
	s1 := arr[2:5]
	s2 := arr[2:]
	s3 := arr[:5]
	s4 := arr[:]
	fmt.Printf("s1 = %v, s2 = %v, s3 = %v, s4 = %v \n", s1, s2, s3, s4)

	//直接创建:方式一
	var slice1 []int
	fmt.Println(slice1)
	slice2 := append(slice1, 2)
	fmt.Println(slice2)

	//直接创建:方式二
	var slice3 = []int{2, 3}
	fmt.Println(slice3)

	//直接创建:方式三,
	//第二个参数：slice的长度
	//第三个参数：slice的容量
	//new返回指针。
	//make返回初始化后的（非零）值。
	temp := make([]int, 3)
	fmt.Println(temp)
	slice4 := make([]int, 3, 3)
	slice5 := make([]int, 3, 10)
	printLenAndCap(slice4)
	printLenAndCap(slice5)
}

// 原始数组改变，所有的slice视图都会改变。
// slice改变，原始数组和对应的slice视图对应的值也会改变。
func sliceAndArrayChange() {
	arr := [10]int{1, 3, 5, 2, 6}
	s1 := arr[2:5]
	s2 := arr[2:]
	fmt.Printf("arr = %v,s1 = %v, s2 = %v \n", arr, s1, s2)
	//改变原始数组,所有视图都改变
	arr[3] = 0
	fmt.Printf("arr = %v,s1 = %v, s2 = %v \n", arr, s1, s2)
	//改变某个slice，其它也会改变
	s1[0] = 0
	fmt.Printf("arr = %v,s1 = %v, s2 = %v \n", arr, s1, s2)
}

//slice的len和cap
func lenAndCap() {
	arr := [10]int{1, 3, 5, 2, 6}
	s1 := arr[2:5]
	printLenAndCap(s1)
	s2 := arr[2:]
	printLenAndCap(s2)

	//cap是隐式的，不可以直接下标取值，但是从该slice新建的slice可以使用cap的长度进行声名
	//fmt.Println(s1[4])
	s3 := s1[:7]
	printLenAndCap(s3)
}

//len实际有的内容的长度，cap是容量(隐式的)
func printLenAndCap(slice []int) {
	fmt.Printf("slice is %v, len is %d, cap  is %d \n", slice, len(slice), cap(slice))
}

//当指向的数组长度不够时，会自动扩容x2，引用一个新建的数组，原始的数组被丢弃。
func ExtendRule() {
	arr := [5]int{1, 3, 5, 2, 6}
	s1 := arr[:]
	printLenAndCap(s1)
	//新加一个元素，cap不够，系统自动扩容x2
	//因为是值传递，所以需要接收返回值
	s2 := append(s1, 23)
	printLenAndCap(s2)
}

//复制
func copySlice() {
	s1 := []int{2, 4, 3}
	s2 := make([]int, 5, 10)
	copy(s2, s1)
	printLenAndCap(s1)
	printLenAndCap(s2)
	fmt.Println("copy")
	s3 := []int{1, 2, 3}
	//直接复制覆盖原来slice
	copy(s3, s1)
	appen := append(s3, s1...)
	fmt.Println("copy exists slice", s3, appen)
}

//删除
func deleteElem() {
	s1 := []int{2, 4, 3}
	s1 = append(s1[:1], s1[2:]...)
	printLenAndCap(s1)
}

func main() {
	InitSlice()
	sliceAndArrayChange()
	lenAndCap()
	ExtendRule()
	copySlice()
	deleteElem()
}
