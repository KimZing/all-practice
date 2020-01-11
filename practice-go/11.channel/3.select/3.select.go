//演示如何解决数据遗漏的问题
package main

import (
	"fmt"
	"time"
)

func generator() chan int {
	ch := make(chan int)
	go func() {
		i := 0
		for {
			time.Sleep(1500 * time.Millisecond)
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
			time.Sleep(1 * time.Second)
			fmt.Println("worker recieved ", <-ch)
		}
	}(ch)
	return ch
}

func main() {

	c1, c2 := generator(), generator()
	worker := createWorker()
	n := 0
	var arr []int
	var activeWorker chan<- int

	//1.控制整体的结束时间
	after := time.After(10 * time.Second)
	//3.每隔固定时间打印缓存的slice长度
	tick := time.Tick(3 * time.Second)

	for {
		var value int
		if len(arr) > 0 {
			activeWorker = worker
			value = arr[0]
		}
		select {
		case n = <-c1:
			arr = append(arr, n)
		case n = <-c2:
			arr = append(arr, n)
		case activeWorker <- value:
			arr = arr[1:]
		case <-after:
			fmt.Println("finish, bye bye")
			return
		//2. 超时时间
		case <-time.After(800 * time.Millisecond):
			fmt.Println("time out")
		case <-tick:
			fmt.Println("slice lens is ", len(arr))
		}
	}

}
