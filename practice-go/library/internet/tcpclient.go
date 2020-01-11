package main

import (
	"fmt"
	"io/ioutil"
	"net"
	"os"
)

func main() {
	server := "127.0.0.1:8080"
	tcpAdd, err := net.ResolveTCPAddr("tcp4", server)
	checkError(err)
	conn, err := net.DialTCP("tcp4", nil, tcpAdd)
	checkError(err)
	_, err = conn.Write([]byte("stamp"))
	checkError(err)
	result, err := ioutil.ReadAll(conn)
	checkError(err)
	fmt.Printf("response is %s \n", string(result))

}

func checkError(err error) {
	if err != nil {
		fmt.Fprintf(os.Stderr, "error is %s \n", err.Error())
		//os.Exit(2)
	}
}
