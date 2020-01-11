/*
Some Test
*/
package main

import (
	"fmt"
	"log"
	"os"
)

//AAAAAAa
//BBBBBBB
/*
Some Comments
*/
func Add(a, b int) int {
	return a + b
}

func main() {
	//创建文件夹
	err := os.Mkdir("hello", 0777)
	checkErr(err)
	//创建多级文件夹
	err = os.MkdirAll("hello/world/boy", 0777)
	checkErr(err)

	//删除单个文件夹
	os.Remove("hello/world/boy")
	//删除多个文件夹
	os.RemoveAll("hello")

	//创建文件
	os.Create("hello.txt")
	//os.NewFile(0777, "world.txt")
	file, _ := os.Open("hello.txt") //只读方式
	defer file.Close()

	//写文件
	file, _ = os.Create("hello.txt")
	file.Write([]byte("Hello File"))

	//读文件
	file, _ = os.Open("hello.txt")
	by := make([]byte, 2)
	for {
		n, _ := file.Read(by)
		if 0 == n {
			break
		}
		fmt.Println(string(by[:n]))
	}

	os.Remove("hello.txt")

}

func checkErr(err error) {
	if err != nil {
		log.Fatal(err)
	}
}
