/**
我们不要一个一个的去接收done返回值，统一接收应该就可以了.但是会出现死锁异常
小写字母可以正常打印，但是大写字母付出错，因为我们接收者写在下面了，小写字母已经放在done channel中了,大写字母仍然放入done ，这时就game over了
有个别手的处理方法，给done<-true也开一个协程
*/
package main

import (
	"fmt"
)

func channelPrint(id int, ch chan int, done chan bool) {
	for {
		fmt.Printf("worker %d recevied %c \n", id, <-ch)
		go func() { done <- true }()
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

	for i, _ := range channels {
		channels[i] <- 'A' + i
	}

	for i, _ := range dones {
		<-dones[i]
		<-dones[i]
	}

}

func main() {
	channelArrayDemo()
}
