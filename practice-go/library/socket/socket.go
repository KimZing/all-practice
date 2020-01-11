package main

import (
	"fmt"
	"golang.org/x/net/websocket"
	"net/http"
)

func Echo(ws *websocket.Conn) {
	for {
		var str string
		websocket.Message.Receive(ws, &str)
		fmt.Println("接收到的数据:" + str)
		websocket.Message.Send(ws, str+": return")
	}
}

func main() {
	http.Handle("/", websocket.Handler(Echo))
	http.ListenAndServe(":1234", nil)
}
