/*
 再次演示一下阻塞
*/
package main

import (
	"fmt"
	"time"
)

func recieve(ch chan int) {
	time.Sleep(time.Second)
	fmt.Printf("接收到：%d \n", <-ch)
	fmt.Printf("接收到：%d \n", <-ch)
	for {
		fmt.Print()
	}
}

func main() {
	ch := make(chan int)
	go recieve(ch)
	ch <- 1
	fmt.Println("1发送完毕")
	ch <- 2
	fmt.Println("2发送完毕")
	ch <- 3
	fmt.Println("3发送完毕")

}
