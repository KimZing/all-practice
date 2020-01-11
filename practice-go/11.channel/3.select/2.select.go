//演示如何传递给其它通道
package main

import (
	"fmt"
)

func generator() chan int {
	ch := make(chan int)
	go func() {
		i := 0
		for {
			ch <- i
			i++
		}
	}()
	return ch
}

func createWorker() chan<- int {
	ch := make(chan int)
	go func(ch chan int) {
		for {
			fmt.Println("worker recieved ", <-ch)
		}
	}(ch)
	return ch
}

func main() {
	/*
		c1, c2 := generator(), generator()
		worker := createWorker()

		//这样的方式会因为worker阻塞而存在问题
		for {
			select {
			case n := <-c1:
				worker <- n
			case n := <-c2:
				worker <- n
			}
		}*/

	c1, c2 := generator(), generator()
	worker := createWorker()
	n := 0
	flag := false
	for {
		var activeWorker chan<- int
		if flag {
			activeWorker = worker
		}
		select {
		case n = <-c1:
			flag = true
		case n = <-c2:
			flag = true
		// ** nil channel会一直阻塞
		case activeWorker <- n:
			flag = false
		}
	}

}
