package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
)

//go中循环中的每个条件都可以进行省略


//计算二进制
func convertToBin(d int) string {
	//for循环可以省略起始条件，
	result := ""
	for ; d > 0; d /= 2 {
		t := d % 2
		result = strconv.Itoa(t) + result
	}
	return result
}

//循环读取文件内容
func readLine() {
	//for可以省略起始和结束条件
	const fileName = "1.basic/2.branch/readme.txt"
	file, err := os.Open(fileName)
	//defer会在代码最后执行，有那么点反转的意思。现在就写出来，可以防止之后忘记关闭文件了
	defer file.Close()
	if err != nil {
		panic(err)
	}

	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		fmt.Println(scanner.Text())
	}

}

//一直打印
func forever() {
	//全部省略，死循环
	for {
		fmt.Println("I am running, never stop!")
	}
}

//扩展，利用goto + if 实现循环

func gotoIf(x int) {
Start:
	if x < 10 {
		fmt.Println(x)
		x++
		goto Start
	}
}

func main() {
	fmt.Println(convertToBin(13))

	readLine()

	gotoIf(5)
	//forever()

	//go中使用rune表示字符，那么我们也可以使用以下方式
	for _, runes := range []rune("hello world") {
		fmt.Printf("%c ", runes)
	}
}
