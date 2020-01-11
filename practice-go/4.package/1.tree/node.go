package tree

import "fmt"

type Node struct {
	Value       int
	Left, Right *Node
}

func (node Node) Println() {
	fmt.Println(node.Value)
}
