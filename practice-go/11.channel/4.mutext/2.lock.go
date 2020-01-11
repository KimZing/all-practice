package main

import (
	"fmt"
	"sync"
	"time"
)

type atomInt struct {
	value int
	lock  sync.Mutex
}

func (a *atomInt) increment() {
	a.lock.Lock()
	defer a.lock.Unlock()
	a.value++
}

func (a *atomInt) get() int {
	a.lock.Lock()
	a.lock.Unlock()
	return a.value
}

//想锁住代码块的做法
func (a *atomInt) lockblock() {
	fmt.Println("start")
	func() {
		a.lock.Lock()
		//dosomething
		a.lock.Unlock()
	}()
	fmt.Println("finish")
}

/*
这时候值就没有问题了，我们可以用 go run -race *.go来查看一下，没有任何提示
*/
func main() {
	n := atomInt{0, sync.Mutex{}}
	n.increment()
	go func() {
		n.increment()
	}()
	time.Sleep(time.Millisecond)
	fmt.Println(n.get())
}
