package kee

import (
	"fmt"
	"net/http"
)

type Kee struct {
	routers map[string]http.HandlerFunc
}

func (kee *Kee) ServeHTTP(response http.ResponseWriter, request *http.Request) {
	method := request.Method;
	path := request.URL.Path;
	key := mergeRequestMethodAndPath(method, path)
	if value, ok := kee.routers[key]; ok {
		value(response, request)
	} else {
		fmt.Fprintf(response, "NOT FOUND: %s", path)
	}
}

func New() *Kee {
	return &Kee{routers: make(map[string]http.HandlerFunc)}
}

func (kee *Kee) AddRouter(method, path string, handler http.HandlerFunc) {
	key := mergeRequestMethodAndPath(method, path)
	kee.routers[key] = handler
}

func (kee *Kee) POST(path string, handler http.HandlerFunc) {
	kee.AddRouter("POST", path, handler)
}

func (kee *Kee) DELETE(path string, handler http.HandlerFunc) {
	kee.AddRouter("DELETE", path, handler)
}

func (kee *Kee) PUT(path string, handlerFunc http.HandlerFunc) {
	kee.AddRouter("PUT", path, handlerFunc)
}

func (kee *Kee) GET(path string, handler http.HandlerFunc) {
	kee.AddRouter("GET", path, handler)
}

func (kee *Kee) Run(addr string) {
	http.ListenAndServe(addr, kee)
}

func mergeRequestMethodAndPath(method, path string) (key string) {
	return fmt.Sprintf("%s:%s", method, path)
}
