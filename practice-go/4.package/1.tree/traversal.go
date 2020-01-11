package tree

//中序遍历
func (node *Node) Traverse() {
	if node == nil {
		return
	}
	node.Left.Traverse()
	node.Println()
	node.Right.Traverse()
}
