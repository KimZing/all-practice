package main

import (
	"errors"
	"fmt"
)

func tryRecover() {
	defer func() {
		re := recover()
		if err, ok := re.(error); ok {
			fmt.Printf("Error cased by %s \n", err.Error())
		} else {
			panic(fmt.Sprintf("i do not know how to deal with %v \n", re))
		}
	}()

	//自定义或者系统的异常
	panic(errors.New("some error"))

	//无法识别的内容
	//panic(123)
}

func main() {
	tryRecover()
}
