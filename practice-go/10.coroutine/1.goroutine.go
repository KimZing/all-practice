/*
1. 协程是轻量级的线程，
2. 非抢占式的多任务处理，由协程主动交出控制权
3. 编译器、解释器、虚拟机层面的多任务
4. 多个协程可能在一个或多个线程上运行
*/
package main

import (
	"fmt"
	"runtime"
	"time"
)

//演示基本的协程操作
func base() {
	for i := 0; i < 10; i++ {
		go func(i int) {
			for {
				fmt.Printf("i is %d \n", i)
			}
		}(i)
	}
	time.Sleep(time.Millisecond)
}

//演示抢占式,没有释放执行权的情况，会一直执行下去
func noRelease() {
	var arr [10]int
	for i := 0; i < 10; i++ {
		go func(i int) {
			for {
				arr[i]++
			}
		}(i)
	}
	fmt.Println(arr)
	time.Sleep(time.Millisecond)
}

//演示非抢占式
func nonrace() {
	var arr [10]int
	for i := 0; i < 10; i++ {
		go func(i int) {
			for {
				arr[i]++
				//手动释放执行权
				runtime.Gosched()
			}
		}(i)
	}
	fmt.Println(arr)
	time.Sleep(time.Millisecond)
}

//演示使用外部变量导致的问题
func outError() {
	var arr [10]int
	for i := 0; i < 10; i++ {
		go func() {
			for {
				//i是外部的，形成闭包，外部变量会最大会变成10
				fmt.Println(i)
				arr[i]++
			}
		}()
	}
	fmt.Println(arr)
	time.Sleep(time.Millisecond)
}

func main() {
	//base()
	//noRelease()
	nonrace()
	//outError()
}
