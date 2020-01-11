package main

import (
	"fmt"
	"net"
)

func main() {
	server := "localhost:8081"
	addr, _ := net.ResolveUDPAddr("udp", server)
	conn, _ := net.ListenUDP("udp", addr)
	for {
		rece := make([]byte, 64)
		i, radd, _ := conn.ReadFromUDP(rece)
		fmt.Println(string(rece[:i]))
		conn.WriteToUDP([]byte("已收到请求"), radd)
	}
}
