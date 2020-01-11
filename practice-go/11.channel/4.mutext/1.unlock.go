package main

import (
	"fmt"
	"time"
)

type atomInt int

func (a *atomInt) increment() {
	*a++
}

func (a *atomInt) get() int {
	return int(*a)
}

/*
这时候值可能错误，我们可以用 go run -race *.go来查看一下
*/
func main() {
	n := atomInt(0)
	n.increment()
	go func() {
		n.increment()
	}()
	time.Sleep(time.Millisecond)
	fmt.Println(n)
}
