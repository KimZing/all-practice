package kee

import (
	"fmt"
	"net/http"
)

type Handler func(context *Context)

type Router struct {
	handlers map[string]Handler
	// 添加一个默认处理器，并且具备可扩展性
	defaultHandler Handler
}

func NewRouter() *Router {
	return &Router{handlers:make(map[string]Handler), defaultHandler:defaultHandler}
}

func (router *Router) addHandler(method string, path string, handler Handler) {
	router.handlers[method+":"+path] = handler
}

func (router *Router) Handle(context *Context) {
	if handler, exist := router.handlers[context.method+":"+context.path]; exist {
		handler(context)
		return
	}
	router.defaultHandler(context)
}

func defaultHandler(context *Context) {
	context.String(http.StatusNotFound, fmt.Sprintf("%s NOT FOUND", context.path))
}
