/*
 在新的协程中没有接收者也是可以的，来过从一开始就会阻塞了
*/
package main

import (
	"fmt"
	"time"
)

var ch = make(chan int)

func send(ch chan int) {
	ch <- 12
	fmt.Println("i am waiting for println 12")
	ch <- 13
	fmt.Println("i am waiting for println 13")
}

func main() {
	go send(ch)
	time.Sleep(5 * time.Second)
}
