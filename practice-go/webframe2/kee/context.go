package kee

import (
	"encoding/json"
	"fmt"
	"net/http"
)

type Context struct {
	writer  http.ResponseWriter
	request *http.Request
	method  string
	path    string
	statusCode int
}

func NewContext(writer http.ResponseWriter, request *http.Request) *Context {
	return &Context{
		writer: writer,
		request: request,
		method: request.Method,
		path: request.URL.Path,
	}
}

func (context *Context) PostForm(key string) string {
	return context.request.FormValue(key)
}

func (context *Context) Query(key string) string {
	return context.request.URL.Query().Get(key)
}

func (context *Context) SetHeader(key string, value string) {
	context.writer.Header().Set(key, value)
}

func (context *Context) Status(code int) {
	context.statusCode = code
	context.writer.WriteHeader(code)
}

func (context *Context) String(code int, format string, values ...interface{}) {
	context.Status(code)
	context.SetHeader("Content-Type", "text/plain")
	context.writer.Write([]byte(fmt.Sprintf(format, values...)))
}

func (context *Context) HTML(code int, html string) {
	context.Status(code)
	context.SetHeader("Content-Type", "text/html")
	context.writer.Write([]byte(html))
}

func (context *Context) JSON(code int, data interface{}) {
	context.Status(code)
	context.SetHeader("Content-Type", "application/json")

	jsonData, err := json.Marshal(data)
	if err != nil {
		fmt.Println(err)
		return
	}
	context.writer.Write(jsonData)
}

func (context *Context) Data(code int, data []byte) {
	context.Status(code)
	context.writer.Write(data)
}
