/*
演示扩展系统或第三方的类型
1. 组合方式
2. 别名方式
*/
package main

import (
	"fmt"
	"practice-go/4.package/1.tree"
	"practice-go/4.package/2.queue"
)

type MyNode struct {
	node *tree.Node
}

func (mnode *MyNode) postOrder() {
	if mnode == nil || mnode.node == nil {
		return
	}
	left := MyNode{mnode.node.Left}
	left.postOrder()
	right := MyNode{mnode.node.Right}
	right.postOrder()
	mnode.node.Println()
}

func main() {
	//演示别名:一：通过使用slice实现一个自定义的队列
	q := queue.Queue{2}
	q.Push(1)
	fmt.Println(q.IsEmpty())
	fmt.Println(q.Pop())
	fmt.Println(q.Pop())
	fmt.Println(q.IsEmpty())

	//演示组合的方式：为我们前面实现的tree.Node添加一个后续遍历
	mnode := MyNode{&tree.Node{3,
		&tree.Node{0, nil, &tree.Node{2, nil, nil}},
		&tree.Node{5, &tree.Node{0, nil, nil}, nil}}}
	mnode.postOrder()
}
