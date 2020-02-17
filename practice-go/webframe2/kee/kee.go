package kee

import "net/http"

type Kee struct {
	router *Router
}

func New() *Kee {
	return &Kee{router: NewRouter()}
}

func (kee *Kee) GET(path string, handler Handler) {
	kee.addRouter("GET", path, handler)
}

func (kee *Kee) POST(path string, handler Handler) {
	kee.addRouter("POST", path, handler)
}

func (kee *Kee) PUT(path string, handler Handler) {
	kee.addRouter("PUT", path, handler)
}

func (kee *Kee) DELETE(path string, handler Handler) {
	kee.addRouter("DELETE", path, handler)
}

func (kee *Kee) addRouter(method, path string, handler Handler) {
	kee.router.addHandler(method, path, handler)
}

func (kee *Kee) Run(addr string) {
	http.ListenAndServe(addr, kee)
}

func (kee *Kee) ServeHTTP(writer http.ResponseWriter, request *http.Request) {
	context := NewContext(writer, request)
	kee.router.Handle(context)
}
