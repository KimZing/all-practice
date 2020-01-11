//变量的声明与赋值
package main

import "fmt"

//先声明然后赋值
func variableZeroValue() {
	var name string
	var age int
	//%s无法打印空字符串，可以使用%q
	//%s打印字符，%d打印十进制，%f打印float,%v打印value,%T打印类型
	fmt.Printf("%q, %d \n", name, age)
	name = "KingBoy"
	age = 25
	fmt.Printf("%q, %d \n", name, age)
}

//声明并赋值，类型相同可以在同一行进行声明
func variableInitialValue() {
	var a, b int = 11, 12
	var s string = "Hello Variable!"
	fmt.Println(a, b, s)
}

//类型可以自动推导，省略类型后可以写在同一行
func variableTypeDeduction() {
	var a, b, s = "a", true, 20
	fmt.Printf("%s %v %d \n", a, b, s)
	fmt.Printf("%T %T %T \n", a, b, s)
}

//更简单的写法
func variableShorter() {
	a, b, c := "a", false, 22
	fmt.Printf("%s %v %d \n", a, b, c)
}

//包变量
var aa int = 12
var bb, cc = "", 21

//包变量不能使用简便写法
//c := 12

//包变量也有更简单的写法，同样适用于方法变量
var (
	dd = 12
	ee = 12
)

//思考，如果包变量和方法变量同名，那么方法中取什么变量的值呢？
var a = 1

func variableInPackageAndFunction() {
	a := 2
	fmt.Println(a) //2 打印的是方法中的变量值
}

//定义过的变量也可以写在`:=`语句中，不过不会重新定义，只会重新赋值
func variableDefineDouble() {
	a := 2
	a, b := 1, 3
	fmt.Println(a, b)
}

func main() {
	variableZeroValue()
	variableInitialValue()
	variableTypeDeduction()
	variableShorter()
	variableInPackageAndFunction()
	variableDefineDouble()
}
