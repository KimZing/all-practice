/**
演示最基本的使用，select 会选择准备好的通道进行处理
*/
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

func main() {
	c1, c2 := generator(), generator()
	for {
		select {
		case n := <-c1:
			fmt.Println("Recieved from c1 :", n)
		case n := <-c2:
			fmt.Println("Recieved from c2 :", n)
		default:
			fmt.Println("none recieved")
		}
	}

}
