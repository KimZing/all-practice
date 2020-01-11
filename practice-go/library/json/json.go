package main

import (
	"encoding/json"
	"fmt"
	"os"
)

type Servers struct {
	Servers []Server
}

type Server struct {
	ServerName string `json:"serverName"`
	ServerIP   string `json:"serverIP"`
}

func main() {
	//json转struct
	sr := Servers{}
	s := `{"servers":[{"serverName":"Shanghai_VPN","serverIP":"127.0.0.1"},{"serverName":"Beijing_VPN","serverIP":"127.0.0.2"}]}`
	err := json.Unmarshal([]byte(s), &sr)
	if err != nil {
		fmt.Println(err)
	}
	fmt.Println(sr)
	//struct转json
	bytes, _ := json.MarshalIndent(sr, " ", "    ")
	os.Stdout.Write(bytes)
}
