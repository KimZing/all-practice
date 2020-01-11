package main

import (
	"fmt"
	"go-practice/7.errorhandle/weberror/filehandle"
	"net/http"
	"os"
)

type UserError interface {
	Error() string
	Message() string
}

type Handle func(http.ResponseWriter, *http.Request) error

func MapError(handle Handle) func(w http.ResponseWriter, r *http.Request) {
	return func(w http.ResponseWriter, r *http.Request) {
		defer func() {

			if err := recover(); err != nil{
				http.Error(w, http.StatusText(http.StatusInternalServerError), http.StatusInternalServerError)
			}
		}()

		err := handle(w, r)
		if err != nil {
			if err, ok := err.(UserError); ok {
				fmt.Println(err.Error())
				http.Error(w, err.Message(), http.StatusBadRequest)
				return
			}

			code := http.StatusOK

			switch {
			case os.IsNotExist(err):
				code = http.StatusNotFound
			case os.IsPermission(err):
				code = http.StatusForbidden
			default:
				code = http.StatusInternalServerError
			}
			http.Error(w, http.StatusText(code), code)
		}
	}
}

func main() {
	http.HandleFunc("/", MapError(filehandle.FileHandle))
	err := http.ListenAndServe(":8888", nil)
	if err != nil {
		panic(err)
	}
}
