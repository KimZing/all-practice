package main

import (
	"errors"
	"fmt"
	"os"
	"strings"
)

func main() {
	//系统异常处理
	_, e := os.Open("notexist.file")
	if e != nil {
		if err, ok := e.(*os.PathError); !ok {
			panic(err)
		} else {
			fmt.Printf("%s %s %s \n",
				err.Op, err.Path, err.Error())
		}
	}
	//自定义异常:一
	err := errors.New("some error")
	fmt.Println(err)

	//也可以将结构体实现Error方法:方式二
	err = MyError{"no reason"}
	fmt.Println(err)
}

type MyError struct {
	reason string
}

func (myError MyError) Error() string {
	return strings.Join([]string{myError.reason}, "")
}
