/**
1. 必通信是建立在两个协程的前提下的
2. 只有两个协程同时存在时才不会发生阻塞
*/
package main

import (
	"fmt"
	"time"
)

//channel是一个goroutine和另外一个goroutine之间的交互，没有另外一个gorotine会死锁
func channelDemo() {
	ch := make(chan int)
	//这里我们开了一个goroutine来处理就不会死锁了,必须写在发送之前
	go func(ch chan int) {
		for {
			fmt.Println(<-ch)
		}
	}(ch)
	ch <- 12
	ch <- 13
	time.Sleep(time.Second)
}

//来个channel数组的demo，通过参数来传递channel
func channelPrint(id int, ch chan int) {
	for {
		fmt.Printf("worker %d recevied %c \n", id, <-ch)
	}
}

func channelArrayDemo() {
	var channels [10]chan int

	for i := 0; i < 10; i++ {
		channels[i] = make(chan int)
		go channelPrint(i, channels[i])
	}

	for i := 0; i < 10; i++ {
		channels[i] <- 'a' + i
	}

	for i := 0; i < 10; i++ {
		channels[i] <- 'A' + i
	}
}

//来个channel数组的demo，通过返回值来传递channel,  chan<- 代表只可以发送数据, 接收数据同理
func createChannelPrint(id int) chan<- int {
	ch := make(chan int)
	go func(ch chan int) {
		for {
			fmt.Printf("worker %d recevied %c \n", id, <-ch)
		}
	}(ch)
	return ch
}

func channelArrayDemo2() {
	var channels [10]chan<- int

	for i := 0; i < 10; i++ {
		channels[i] = createChannelPrint(i)
	}

	for i := 0; i < 10; i++ {
		channels[i] <- 'a' + i
	}

	for i := 0; i < 10; i++ {
		channels[i] <- 'A' + i
	}
}

//带有缓存的channel, ps：如果必须等人收(切换协程)比较耗费资源，所以可以利用缓存
func channelCacheDeme() {
	ch := make(chan int, 2)
	ch <- 1
	ch <- 2
}

//channel是可以关闭的
func closeChannel() {
	ch := make(chan int, 2)
	go worker(ch)
	ch <- 1
	ch <- 2
	close(ch)
}

func worker(ch chan int) {
	//这种方式还会不停的接收
	//for {
	//	fmt.Printf("recevied %d \n", <- ch)
	//}

	//处理方式一
	//for {
	//	if n, ok := <- ch; ok {
	//		fmt.Printf("a recevied %d \n", n)
	//	} else {
	//		break
	//	}
	//}

	//处理方式二
	for n := range ch {
		fmt.Printf("b recevied %d \n", n)
	}
}

func main() {
	channelDemo()
	channelArrayDemo()
	channelArrayDemo2()
	closeChannel()

	time.Sleep(time.Second)
}
