package main

import (
	"encoding/xml"
	"fmt"
	"io/ioutil"
)

type Servers struct {
	XMLName     xml.Name `xml:"servers"`
	Version     string   `xml:"version,attr"`
	Server      []Server `xml:"server"`
	Description string   `xml:",innerxml"`
}

type Server struct {
	XMLName xml.Name `xml:"server"`
	Name    string   `xml:"serverName"`
	Ip      string   `xml:"serverIP"`
}

func main() {

	//读取xml

	data, err := ioutil.ReadFile("resources/server.xml")
	if err != nil {
		fmt.Println("文件出错", err)
	}

	servers := Servers{}
	err = xml.Unmarshal(data, &servers)
	if err != nil {
		fmt.Println("转换出错", err)
	}

	fmt.Println(servers)

	//创建xml
	servers.Description = ""
	bytes, _ := xml.MarshalIndent(servers, "  ", "    ")
	bytes = append([]byte(xml.Header), bytes...)

	ioutil.WriteFile("resources/king.xml", bytes, 777)
}
