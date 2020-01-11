package filehandle

import (
	"io/ioutil"
	"net/http"
	"os"
	"strings"
)

type UserError struct {
	userMsg string
	systemMsg string
}

func (err UserError) Error() string {
	return err.systemMsg
}

func (err UserError) Message() string {
	return err.userMsg
}

func FileHandle(writer http.ResponseWriter, request *http.Request) error {
	requestPath := request.URL.Path
	if contains := strings.Contains(requestPath, "/content"); !contains {
		return UserError{userMsg:"请求路径错误", systemMsg:"另外一个写代码的把路径控制错了"}
	}
	filePath := requestPath[len("/content/"):]
	file, err := os.Open(filePath)
	if err != nil {
		return err
	}
	bytes, err := ioutil.ReadAll(file)
	if  err != nil{
		return err
	}
	writer.Write(bytes)
	return nil
}
