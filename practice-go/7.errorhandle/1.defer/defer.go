/*
defer会在方法结束时执行，
会将所有的defer语句放入一个栈的结构中，先进后出
即使遇到return或者panic也同样执行
*/
package main

import (
	"bufio"
	"fmt"
	"os"
)

//基本的用法
func tryDefer() {
	defer fmt.Println(1)
	defer fmt.Println(2)
	//panic(" some error ")
	fmt.Println(4)

	for i := 0; i < 30; i++ {
		defer fmt.Println(i)
		if i > 20 {
			panic("too many number")
		}
	}
}

const filePath = "7.errorhandle/1.defer/file.txt"

//操作资源
func writeFile() {
	file, e := os.Create(filePath)
	defer os.Remove(filePath) //创建后删除了
	defer file.Close()
	if e != nil {
		panic(e)
	}

	writer := bufio.NewWriter(file)
	defer writer.Flush()
	fmt.Fprintln(writer, "Hello")
}

func main() {
	//tryDefer()
	writeFile()
}
