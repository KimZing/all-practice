package main

import (
	"fmt"
	"net/rpc"
)

type Nums struct {
	X, Y int
}

func main() {
	n := Nums{12, 3}
	client, _ := rpc.DialHTTP("tcp", "localhost:8080")
	var reply int
	error := client.Call("T.Mul", n, &reply)
	fmt.Println(error)
	fmt.Println(n, reply)

}
