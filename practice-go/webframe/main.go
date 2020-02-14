package main

import (
	"fmt"
	"net/http"
	"practice-go/webframe/kee"
)

func main() {
	kee := kee.New()
	kee.GET("/user", getUser())
	kee.Run(":8080")
}

func getUser() func(response http.ResponseWriter, request *http.Request) {
	return func(response http.ResponseWriter, request *http.Request) {
		fmt.Fprintln(response, "🍀🍀🍀🍀🍀🍀🍀🍀🍀")
	}
}