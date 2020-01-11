/**
使用系统自带的waitGroup   add  done wait
*/
package main

import (
	"fmt"
	"sync"
)

func channelPrint(id int, ch chan int, wg *sync.WaitGroup) {
	for {
		fmt.Printf("worker %d recevied %c \n", id, <-ch)
		wg.Done()
	}
}

func channelArrayDemo() {
	var channels [10]chan int

	wg := sync.WaitGroup{}
	wg.Add(20)

	for i, _ := range channels {
		channels[i] = make(chan int)
		go channelPrint(i, channels[i], &wg)
	}

	for i, _ := range channels {
		channels[i] <- 'a' + i
	}

	for i, _ := range channels {
		channels[i] <- 'A' + i
	}

	wg.Wait()

}

func main() {
	channelArrayDemo()
}
