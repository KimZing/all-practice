package main

import (
	"practice-go/7.errorhandle/3.weberror/filehandle"
	"log"
	"net/http"
	_ "net/http/pprof"
	"os"
)

//这里我们定义一个错误接口，handler只要实现相应方法就可以，不用互相知道彼此
type UserError interface {
	error            //相当于 Error() string,  这个是给系统内部看的
	Message() string //这个是返回给用户看的
}

type Handler func(http.ResponseWriter, *http.Request) error

func MapError(handler Handler) func(http.ResponseWriter, *http.Request) {
	return func(writer http.ResponseWriter, request *http.Request) {
		defer func() {
			if r := recover(); r != nil {
				log.Printf("Panic: %v", r)
				http.Error(writer,
					http.StatusText(http.StatusInternalServerError),
					http.StatusInternalServerError)
			}
		}()

		err := handler(writer, request)
		if err != nil {
			log.Printf("error is %s", err.Error())

			//用户异常
			if err, ok := err.(UserError); ok {
				//这里返回的Message(),给用户看，而上面打印的err.Error()，给系统看，这样的设计很好，相互隔离
				http.Error(writer, err.Message(), http.StatusBadRequest)
				return
			}

			//系统异常
			code := http.StatusOK
			switch {
			case os.IsNotExist(err):
				code = http.StatusNotFound
			default:
				code = http.StatusInternalServerError
			}
			http.Error(writer, http.StatusText(code), code)
		}
	}
}

func main() {
	http.HandleFunc("/", MapError(filehandle.Handle))
	err := http.ListenAndServe("localhost:8888", nil)
	if err != nil {
		panic(err)
	}
}
