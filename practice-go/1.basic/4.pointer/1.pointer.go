/*
Go 具有指针。 指针保存了变量的内存地址。

类型 *T 是指向类型 T 的值的指针。其零值是 `nil`。

var p *int
& 符号会生成一个指向其作用对象的指针。

i := 42
p = &i
* 符号表示指针指向的底层的值。

fmt.Println(*p) // 通过指针 p 读取 i
*p = 21         // 通过指针 p 设置 i
这也就是通常所说的“间接引用”或“非直接引用”。
*/
package main

import "fmt"

func pointer() {
	a := 2
	p := &a
	*p = 3
	fmt.Println(a)
}

func swap(a, b *int) {
	*a, *b = *b, *a
}

func swap2(a, b int) (int, int) {
	return b, a
}

func main() {
	pointer()
	a, b := 2, 3
	swap(&a, &b)
	fmt.Println(a, b)
	a, b = swap2(3, 4)
	fmt.Println(a, b)
}
