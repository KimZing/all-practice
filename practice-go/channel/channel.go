package main

import (
	"fmt"
	"sync"
)

func Worker(i int, w worker) {
	for n := range w.c {
		fmt.Printf("recieved from worker %d with %c \n",  i, n)
		w.done()
	}
}

type worker struct {
	c chan int
	done func()
}

func CreateWorker(d int, wg *sync.WaitGroup) worker{
	worker := worker{make(chan int), func() {
		wg.Done()
	}}
	go Worker(d, worker)
	return worker
}

func Sender() {
	var workers [10]worker
	var wg sync.WaitGroup
	for i := range workers {
		workers[i] = CreateWorker(i, &wg)
	}
	wg.Add(20)
	for i := range workers {
		workers[i].c <- 'a' + i
	}
	for i := range workers  {
		workers[i].c <- 'A' + i
	}
	wg.Wait()

}

func main() {
	Sender()
}
