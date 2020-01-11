package filehandle

import (
	"io/ioutil"
	"log"
	"net/http"
	"os"
	"strings"
)

type UserError string

func (errStr UserError) Error() string {
	return errStr.Message()
}

func (errStr UserError) Message() string {
	return string(errStr)
}

const prefix = "/content/"

func Handle(writer http.ResponseWriter, request *http.Request) error {

	if !strings.Contains(request.URL.Path, prefix) {
		return UserError("path must start with " + prefix)
	}

	filePath := request.URL.Path[len(prefix):]
	log.Printf("request file path is %s \n", filePath)
	file, e := os.Open(filePath)
	defer file.Close()
	if e != nil {
		return e
	}
	contents, e := ioutil.ReadAll(file)
	if e != nil {
		return e
	}
	writer.Write(contents)
	return nil
}
