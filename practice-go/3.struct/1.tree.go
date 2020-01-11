//go语言仅支持封装，不支持多态和继承
//go面向接口编程
//TODO 结构体中可以定义匿名字段

//使用值接收者还是指针接收者
//1.要改变内容使用指针接收者
//2.结构体过大，考虑使用指针接收者
//3.一致性：如果有指针接收者，建议统一

//ps: 值接收者才是go特有的
package main

import "fmt"

//结体的定义
type treeNode struct {
	value       int
	left, right *treeNode
}

//结构体的创建方式
func create() {
	//方式一,zero value
	var t1 treeNode
	fmt.Println(t1)
	//方式二，通过new关键字,   &{0 <nil> <nil>},new返回的是指针,而make返回的是初始化后的非零值
	var t2 *treeNode = new(treeNode)
	fmt.Println(t2)
	//方式三,通过field的名称来赋值，顺序可以不一致
	t3 := treeNode{left: &treeNode{}, value: 2}
	fmt.Println(t3)
	//方式四，按照属性的声明顺序
	t4 := treeNode{1, nil, &treeNode{value: 2}}
	fmt.Println(t4)

	//slice
	nodes := []treeNode{{}, {0, nil, &treeNode{}}, {value: 12}}
	fmt.Println(nodes)
}

//有时，我们需要自定义的构造
//思考：该方法返回了局部变量，那么到底是放在堆还是栈？
func createNode(value int) *treeNode {
	return &treeNode{value: value}
}

//为结构体定义方法
//1.和普通方法没有区别，只是将结构体提到了前面，相当于有了关联
//2.go中所有参数都是值传递，会复制一份，大的结构体会影响效率。
//3.无法更改原值
func (node treeNode) println() {
	fmt.Println(node.value)
}

//为结构体定义指针方法
//只有使用指针传递才可以修改结构体内容
func (node *treeNode) setValue(value int) {
	node.value = value
}

//调用的一些有趣之处
func invoke() {
	//go中对象和指针都可以调用普通或者指针方法，go会帮助我们自动转。当然你也可以按照严格的方式来写
	//通过对象直接调用
	root := treeNode{value: 3}
	root.setValue(4)
	root.println()
	//通过指针调用
	proot := &root
	proot.setValue(5)
	proot.println()
}

//go中nil指针可以调用指针方法(不可以调用普通方法)，但是不可以取值
func invokeNil() {
	var root *treeNode
	fmt.Println(root) //nil
	root.hello()

	//取值会报错
	//fmt.Println(root.value)
	//所以我们需要对指针方法进行一定的处理
	//我们改造一下setValue方法
	root.setValue2(12)

}

func (node *treeNode) hello() {
	fmt.Println("Hello Nil!")
}

func (node *treeNode) setValue2(value int) {
	if node == nil {
		fmt.Println("node is nil!")
		return
	}
	node.value = value
}

//中序遍历
func (node *treeNode) traverse() {
	if node == nil {
		return
	}
	node.left.traverse()
	node.println()
	node.right.traverse()
}

func main() {
	create()
	fmt.Println(createNode(11))
	invoke()
	invokeNil()

	root := treeNode{3,
		&treeNode{0, nil, &treeNode{2, nil, nil}},
		&treeNode{5, &treeNode{0, nil, nil}, nil}}
	root.traverse()

}
