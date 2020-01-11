package main

import (
	"fmt"
	"net/http"
)

func main() {
	i := Index;
	http.HandleFunc("/", i)
}
func Index(w http.ResponseWriter, r *http.Request){
	fmt.Fprint(w,"Blog:www.flysnow.org\nwechat:flysnow_org")
}

