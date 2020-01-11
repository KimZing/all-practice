package main

import (
	"net/http"
	"net/rpc"
)

type Nums struct {
	X, Y int
}

type T int

func (t *T) Mul(n *Nums, r *int) error {
	*r = n.X * n.Y
	return nil
}

func main() {
	t := new(T)
	rpc.Register(t)
	rpc.HandleHTTP()
	http.ListenAndServe(":8080", nil)
}
