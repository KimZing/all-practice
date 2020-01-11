/*
	bool string

	(u)int (u)int8 (u)int16 (u)int32 (u)int64 uintptr
	1. u是无符号数
	2. (u)int 长度由系统的位数来决定
	3. uintptr 是指针类型
	4. 尽管int的长度是32 bit, 但int 与 int32并不可以互用。

	byte rune
	1. byte 一个字节
	2. rune 字符,4字节，32位
	3. rune是int32的别称，byte是uint8的别称。

	float32, float64, complex64, complex128
	1. complex64 实数32位，虚数32位，complex128同理
*/
package main

import (
	"fmt"
	"math"
	"math/cmplx"
)

func stringDefine() {
	a := "hello"
	//源格式输出，这货不是单引号
	b := `hello
			world!`
	//字符串截取,包首不包尾
	c := a[1:3]
	fmt.Println(a, "\n", b, "\n", c)

}

//欧拉公式
func euler() {
	//无限接近0
	fmt.Println(cmplx.Exp(1i * math.Pi) + 1)
	fmt.Printf("%.3f \n", cmplx.Exp(1i * math.Pi) + 1)
}

//类型转换示例,三角函数
func triangle() {
	var a, b int = 3, 4
	var c int
	c = int(math.Sqrt(float64(a*a + b*b)))
	fmt.Println(c)
}

//常量
//常量是没有类型的，但当进行赋值时，会转换为默认类型
func consts() {
	const name = "kingboy"
	const a, b = 3, 4
	//常量可以被当做各种类型来使用，除非手动指定类型
	var c = int(math.Sqrt(a * a + b * b))
	fmt.Println(name, c)
}

//枚举
func enums() {
	//1.默认值为0
	//2.每遇到一个const，值重置为0
	//3.同一行，值相同，const中每增加一行加1

	//普通枚举类型
	const (
		java = iota
		_
		cpp
		golang
		php
	)
	fmt.Println(java, cpp, golang, php)
	//自增值枚举类型
	const (
		b = 1 << (10 * iota)
		kb
		mb
		gb
		tb
		pb
	)
	fmt.Println(b, kb, mb, gb, tb, pb)
}

func main() {
	stringDefine()
	euler()
	triangle()
	consts()
	enums()
}
