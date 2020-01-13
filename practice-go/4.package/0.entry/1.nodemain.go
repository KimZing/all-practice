/*
名字一般使用CamelCase
1. 首字母小写表示private
2. 首字母大写表示public
3. private、public是针对包来说的

包
1.一个目录有且仅有一个包
2.目录名可以和包名一样
3.main包只能有一个，包含程序入口
4.为结构体定义的方法必须放入一个包内，但是可以不是一个文件
*/
package main

import (
	"practice-go/4.package/1.tree"
)

//我们对 `3.struct`进行改造,
func main() {
	root := tree.Node{3,
		&tree.Node{0, nil, &tree.Node{2, nil, nil}},
		&tree.Node{5, &tree.Node{0, nil, nil}, nil}}
	root.Traverse()
}
