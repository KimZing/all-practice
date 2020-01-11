package main

import (
	"fmt"
	"net/rpc/jsonrpc"
)

type Username struct {
	First, Second string
}

func main() {
	client, error := jsonrpc.Dial("tcp", "localhost:8080")
	fmt.Println(error)
	u := Username{"King", "Boy"}
	var s string
	client.Call("U.Name", u, &s)
	fmt.Println(s)
}
