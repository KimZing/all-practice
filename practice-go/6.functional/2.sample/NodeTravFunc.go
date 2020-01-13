package main

import (
	"fmt"
	"practice-go/4.package/1.tree"

)

//复习下使用组合的方式进行扩展方法
type Node struct {
	node *tree.Node
}

func (n *Node) TravFunc(f func(node Node)) {
	if n == nil || n.node == nil {
		return
	}
	nl := Node{n.node.Left}
	nl.TravFunc(f)
	f(*n)
	nr := Node{n.node.Right}
	nr.TravFunc(f)
}

//TODO 复习下使用别名的方式进行扩展方法
type MNode tree.Node

func (mn MNode) TravMFunc(f func(node MNode)) {

	if l := mn.Left; l != nil {
		MNode(*l).TravMFunc(f)
	}
	if &mn != nil {
		f(mn)
	}
	if r := mn.Right; r != nil {
		MNode(*r).TravMFunc(f)
	}

}

//使用channel来实现更加顺畅的操作
func (mn MNode) TraveWithChannel() chan MNode {
	out := make(chan MNode)
	go func() {
		mn.TravMFunc(func(node MNode) {
			out <- node
		})
		//由发送者关闭
		close(out)
	}()
	return out
}

func main() {
	root := tree.Node{3,
		&tree.Node{0, nil, &tree.Node{2, nil, nil}},
		&tree.Node{5, &tree.Node{0, nil, nil}, nil}}

	//组合,总结一下，组合可以通过包装的方式去使用之前的对象结构
	node := Node{&root}
	//实现自定义的计数功能
	count := 0
	node.TravFunc(func(node Node) {
		count++
	})
	fmt.Println(count)

	//别名：通过强转的方式去当做之前的替身一样
	MNode(root).TravMFunc(func(node MNode) {
		count++
	})
	fmt.Println(count)

	//顺便说说：指针调用普通方法时，go会隐式转换为对应的值，但是为空时会报错的哈
	//空指针是可以调用方法的，不过如果其中用到了空指针的属性，会报错的，原理同上，要做空判断

	fmt.Println()

	chnode := MNode(root).TraveWithChannel()
	max := 0
	for n := range chnode {
		if n.Value > max {
			max = n.Value
		}
	}
	fmt.Println(max)
}
