/*
Go 语言的取地址符是 &，放到一个变量前使用就会返回相应变量的内存地址。
var a int = 10
fmt.Printf("变量的地址: %x\n", &a  )

一个指针变量指向了一个值的内存地址。
var var_name *var-type
var ip *int        指向整型
var fp *float32    指向浮点型

定义指针变量。
a := 1
为指针变量赋值。
var p *int = &a
访问指针变量中指向地址的值。
fmt.Printf("*ip 变量的值: %d\n", *ip )

当一个指针被定义后没有分配到任何变量时，它的值为 nil。
var  ptr *int
fmt.Printf(ptr)  // nil

1 当使用&操作符对普通变量进行取地址操作并得到变量的指针后，可以对指针使用*操作符，也就是指针取值

2 变量、指针和地址三者的关系是，每个变量都拥有地址，指针的值就是地址。

3 取地址操作符&和取值操作符*是一对互补操作符

4 变量、指针地址、指针变量、取地址、取值的相互关系和特性如下：
对变量进行取地址操作使用&操作符，可以获得这个变量的指针变量。
指针变量的值是指针地址。
对指针变量进行取值操作使用*操作符，可以获得指针变量指向的原变量的值。
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

func testPointSwap(m, n *int) {
	// 传递的永远是值,此方法传递的是指针的值 m和n 在这里已经被复制了一份
	// 指针的内存地址是新的
	fmt.Printf("指针地址中,&m:%p, &n:%p\n", &m, &n)
	// 指针的值还是原来的值
	fmt.Printf("转换中指针地址1,m:%p, n:%p, 值,m:%d n:%d\n", m, n, *m, *n)
	//交换了m,n的值，并不会影响外部指针的值
	n, m = m, n
	// 内部的确实交换了
	fmt.Printf("转换中指针地址2,m:%p, n:%p, 值,m:%d n:%d\n", m, n, *m, *n)
}

func main() {
	pointer()
	a, b := 2, 3
	swap(&a, &b)
	fmt.Println(a, b)
	a, b = swap2(3, 4)
	fmt.Println(a, b)

	m := 1
	n := 2
	fmt.Printf("转换前指针地址,m:%p, n:%p, 值,m:%d n:%d\n", &m, &n, m, n)
	m1 := &m
	n1 := &n
	fmt.Printf("指针地址前,&m:%p, &n:%p\n", &m1, &n1)
	testPointSwap(&m, &n)
	fmt.Printf("转换后指针地址,m:%p, n:%p, 值,m:%d n:%d\n", &m, &n, m, n)
	fmt.Printf("指针地址后,&m:%p, &n:%p\n", &m1, &n1)

}
