package main

import (
	"io/ioutil"
	"net/http"
	"os"
)

const url = "/file/"

func main() {
	http.HandleFunc(url, func(writer http.ResponseWriter, request *http.Request) {
		filePath := request.URL.Path[len(url):]
		file, err := os.Open(filePath)
		if err != nil {
			panic(err)
		}
		bytes, err := ioutil.ReadAll(file)
		writer.Write(bytes)
	})
	err := http.ListenAndServe( ":8888", nil)
	if err != nil {
		panic(err)
	}
}
