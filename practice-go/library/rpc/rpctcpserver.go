package main

import (
	"net"
	"net/rpc"
)

type Nums struct {
	X, Y int
}

type T int

func (t *T) MulNum(n Nums, r *int) error {
	*r = n.Y * n.X
	return nil
}

func main() {
	t := new(T)
	rpc.Register(t)
	addr, _ := net.ResolveTCPAddr("tcp4", "localhost:8080")
	listener, _ := net.ListenTCP("tcp4", addr)
	for {
		conn, _ := listener.Accept()
		rpc.ServeConn(conn)
		conn.Close()
	}
}
