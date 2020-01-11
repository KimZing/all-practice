/**
 这样改变之后，变成了顺序打印，很明显不是我们想要的
 ps： 可以定义一个结构体，更加抽象
 type worker struct {
	in  chan int
	out chan bool
 }
*/
package main

import (
	"fmt"
)

func channelPrint(id int, ch chan int, done chan bool) {
	for {
		fmt.Printf("worker %d recevied %c \n", id, <-ch)
		done <- true
	}
}

func channelArrayDemo() {
	var channels [10]chan int
	var dones [10]chan bool

	for i, _ := range channels {
		channels[i] = make(chan int)
		dones[i] = make(chan bool)
		go channelPrint(i, channels[i], dones[i])
	}

	for i, _ := range channels {
		channels[i] <- 'a' + i
		<-dones[i]
	}

	for i, _ := range channels {
		channels[i] <- 'A' + i
		<-dones[i]
	}
}

func main() {
	channelArrayDemo()
}
