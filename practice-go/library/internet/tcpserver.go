package main

import (
	"fmt"
	"net"
	"os"
	"strconv"
	"strings"
	"time"
)

func main() {
	server := "localhost:8080"
	laddr, err := net.ResolveTCPAddr("tcp4", server)
	checkError2(err)
	listener, err := net.ListenTCP("tcp", laddr)
	checkError2(err)
	for {
		conn, err := listener.Accept()
		if err != nil {
			fmt.Fprint(os.Stderr, "data error is %s", err.Error())
			continue
		}
		fmt.Println("链接已经建立")
		go handlerClient(conn)
	}

}

func handlerClient(conn net.Conn) {
	conn.SetDeadline(time.Now().Add(5 * time.Second))
	content := make([]byte, 64)
	defer conn.Close() //只有当for循环超时，才关闭，这就是长连接
	for {

		size, _ := conn.Read(content)
		sc := strings.TrimSpace(string(content[:size]))
		fmt.Println(sc)

		if size == 0 {
			break
		} else if sc == "stamp" {
			conn.Write([]byte(strconv.FormatInt(time.Now().Unix(), 10)))
		} else {
			conn.Write([]byte(time.Now().String()))
		}
		//conn.Close()   请求完就关闭这是短链接
		content = make([]byte, 64)
	}

}

func checkError2(error error) {
	if error != nil {
		fmt.Fprintf(os.Stderr, "error is %s \n", error.Error())
		os.Exit(2)
	}
}
