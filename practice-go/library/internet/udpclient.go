package main

import (
	"fmt"
	"net"
)

func main() {
	server := "localhost:8081"
	addr, _ := net.ResolveUDPAddr("udp", server)
	conn, _ := net.DialUDP("udp", nil, addr)
	conn.Write([]byte("write anything"))

	receive := make([]byte, 64)
	i, _ := conn.Read(receive)
	fmt.Println(string(receive[:i]))
}
