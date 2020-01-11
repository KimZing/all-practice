package main

import (
	"fmt"
	"time"
)

func write(c chan int) {
	for i := 0; ; i++  {
		c <- i
	}
}

func main() {
	c1 := make(chan int)
	c2 := make(chan int)
	go write(c1)
	go write(c2)
	after := time.After(8 * time.Second)
	tick := time.Tick(time.Millisecond)
	for {
		select {
		case <- c1:
			fmt.Println("Received From c1")
		case <- c2:
			fmt.Println("Received From c2")
		case <- after:
			fmt.Println("timeout")
			return
		case <- tick:
			fmt.Println("in time==============")
		default:
			fmt.Println("nothing received")
		}
	}

}
