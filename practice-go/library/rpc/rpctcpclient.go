package main

import (
	"fmt"
	"net/rpc"
)

type Nums struct {
	X, Y int
}

func main() {
	client, _ := rpc.Dial("tcp", "localhost:8080")
	n := Nums{3, 4}
	var i int
	client.Call("T.MulNum", n, &i)
	fmt.Println(n, i)
}
