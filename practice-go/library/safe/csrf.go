package main

import (
	"crypto/md5"
	"io"
	"strconv"
	"time"
)

func main() {
	//TODO 未完待续
	h := md5.New()
	io.WriteString(h, strconv.FormatInt(time.Now().Unix(), 10))
}
