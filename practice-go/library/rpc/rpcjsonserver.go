package main

import (
	"net"
	"net/rpc"
	"net/rpc/jsonrpc"
)

type Username struct {
	First, Second string
}

type U string

func (u *U) Name(name Username, s *string) error {
	*s = "Hello" + name.First + name.Second
	return nil
}

func main() {
	u := new(U)
	rpc.Register(u)
	addr, _ := net.ResolveTCPAddr("tcp", "localhost:8080")
	listener, _ := net.ListenTCP("tcp", addr)
	for {
		conn, _ := listener.Accept()
		jsonrpc.ServeConn(conn)
		conn.Close()
	}
}
