package kee

import (
	"fmt"
	"net/http"
)

type Kee struct {

}

func (kee *Kee) ServeHTTP(writer http.ResponseWriter, request *http.Request) {
	switch path := request.URL.Path; path{
	case "/214":
		fmt.Fprintln(writer, "<body style='background-size:100%;background:url(" +
			"\"http://images.kimzing.com/images/1.jpg\") no-repeat'><div style='margin-top:200px;" +
			"text-align:center;border:3px dashed lightBlue'><h1 style=\"color:DeepPink\">宝宝</h1> " +
			"<span style='font-size:35px;color:red;font-family:serif;'>🍀 我🌹爱🌹你 🍀</span></div></body>")
	case "/hello":
		fmt.Fprintln(writer, "hello")
	default:
		fmt.Fprintln(writer, "path not found")
	}
}

func New() *Kee{
	return new(Kee)
}

func (kee *Kee) Run(addr string) {
	http.ListenAndServe(addr, kee)
}