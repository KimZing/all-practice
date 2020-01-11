/**
在上个例子中，我们也可以分开接收，就不会有死锁
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
	}

	for i, _ := range dones {
		<-dones[i]
	}

	for i, _ := range channels {
		channels[i] <- 'A' + i
	}

	for i, _ := range dones {
		<-dones[i]
	}
}

func main() {
	channelArrayDemo()
}
