package main

import (
	"bufio"
	"fmt"
	"io"
	"strings"
)

func fibonacci() fibGen {
	var a, b = 0, 1
	return func() int {
		a, b = b, a+b
		return a
	}
}

type fibGen func() int

func (f fibGen) Read(p []byte) (n int, err error) {
	fibNum := f()
	if fibNum > 1000 {
		return 0, io.EOF
	}
	s := fmt.Sprintf("%d\n", fibNum)
	return strings.NewReader(s).Read(p)
}

func main() {
	scanner := bufio.NewScanner(fibonacci())
	for scanner.Scan() {
		fmt.Println(scanner.Text())
	}
}
