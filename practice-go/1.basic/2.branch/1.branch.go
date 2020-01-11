package main

import (
	"fmt"
	"io/ioutil"
)

//if条件
func readFile() {
	const fileName = "1.basic/2.branch/readme.txt"
	//不用写括号
	//条件语句前可以进行赋值语句
	if contents, err := ioutil.ReadFile(fileName); err != nil {
		fmt.Println(err)
	} else {
		fmt.Printf("%s \n", contents)
	}
}

//switch语句，有条件
func caculate(a, b int, operation string) int {
	//switch默认不会执行下一个分支，可以使用fallthrough执行下一个判断
	switch operation {
	case "+":
		return a + b
	case "-":
		return a - b
	case "*":
		return a * b
	case "/":
		return a / b
	default:
		//会让程序终止执行
		panic("operation is not support!")
	}
}

//switch可以不写条件，觉得更加灵活
func level(score int) {
	switch {
	case score < 0 || score > 100:
		panic("score is wrong number!")
	case score < 60:
		fmt.Println("F")
	case score < 70:
		fmt.Println("C")
	case score < 80:
		fmt.Println("B")
	case score <= 100:
		fmt.Println("A")
	}
}
func main() {
	readFile()
	fmt.Println(caculate(1, 2, "+"))
	fmt.Println(caculate(1, 2, "-"))
	fmt.Println(caculate(1, 2, "*"))
	fmt.Println(caculate(1, 2, "/"))
	//caculate(1, 2, "^")

	level(30)
	level(60)
	level(71)
	level(92)
	//level(101)
}
