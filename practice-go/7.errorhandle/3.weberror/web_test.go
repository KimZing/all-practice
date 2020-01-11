package main

import (
	"errors"
	"fmt"
	"io/ioutil"
	"net/http"
	"net/http/httptest"
	"os"
	"strings"
	"testing"
)

//panic error
func panicError(w http.ResponseWriter, r *http.Request) error {
	panic("123")
}

//user error
func testingUserError(w http.ResponseWriter, r *http.Request) error {
	return testUserError("User Error Test")
}

type testUserError string

func (errStr testUserError) Error() string {
	return errStr.Message()
}

func (errStr testUserError) Message() string {
	return string(errStr)
}

//NotExist
func testingNotExit(w http.ResponseWriter, r *http.Request) error {
	return os.ErrNotExist
}

//DefaultError
func testingDefaultError(w http.ResponseWriter, r *http.Request) error {
	return errors.New("default error")
}

//NoError
func testingNoError(w http.ResponseWriter, r *http.Request) error {
	fmt.Fprint(w, "no error")
	return nil
}

var datas = []struct {
	handle Handler
	code   int
	body   string
}{
	{panicError, 500, "Internal Server Error"},
	{testingUserError, 400, "User Error Test"},
	{testingNotExit, 404, "Not Found"},
	{testingDefaultError, 500, "Internal Server Error"},
	{testingNoError, 200, "no error"},
}

//模拟代码测试
func TestMapError(t *testing.T) {

	for _, tt := range datas {
		function := MapError(tt.handle)
		response := httptest.NewRecorder()
		request := httptest.NewRequest(http.MethodGet, "http://www.baidu.com", nil)
		function(response, request)

		verify(response.Result(), tt.code, tt.body, t)
	}

}

//真实服务测试
func TestMapperErrorInServer(t *testing.T) {
	for _, tt := range datas {
		f := MapError(tt.handle)
		server := httptest.NewServer(http.HandlerFunc(f))
		response, _ := http.Get(server.URL)

		verify(response, tt.code, tt.body, t)
	}
}

func verify(response *http.Response, code int, body string, t *testing.T) {
	bytes, _ := ioutil.ReadAll(response.Body)
	b := strings.Trim(string(bytes), "\n")
	if b != body || response.StatusCode != code {
		t.Errorf("got body is %s expected %s, got code is %d expected %d",
			b, body, response.StatusCode, code)
	}
}
