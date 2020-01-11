package main

import (
	"crypto/md5"
	"crypto/sha1"
	"crypto/sha256"
	"fmt"
	"io"
)

func main() {
	//传统加密
	h := sha1.New()
	io.WriteString(h, "His money is twice tainted: 'taint yours and 'taint mine.")
	fmt.Printf("% x\n", h.Sum(nil))

	h = sha256.New()
	io.WriteString(h, "His money is twice tainted: 'taint yours and 'taint mine.")
	fmt.Printf("% x\n", h.Sum(nil))

	h = md5.New()
	io.WriteString(h, "需要加密的密码")
	fmt.Printf("%x\n", h.Sum(nil))

	//加盐
	h = md5.New()
	io.WriteString(h, "需要加密的密码")
	//pwmd5等于e10adc3949ba59abbe56e057f20f883e
	pwmd5 := fmt.Sprintf("%x", h.Sum(nil))
	//指定两个 salt： salt1 = @#$%   salt2 = ^&*()
	salt1 := "@#$%"
	salt2 := "^&*()"
	//salt1+用户名+salt2+MD5拼接
	io.WriteString(h, salt1)
	io.WriteString(h, "abc")
	io.WriteString(h, salt2)
	io.WriteString(h, pwmd5)
	fmt.Printf("%x", h.Sum(nil))

	//专家级
	//目前Go语言里面支持的库http://code.google.com/p/go/source/browse?repo=crypto#hg%2Fscrypt
	//dk := scrypt.Key([]byte("some password"), []byte(salt), 16384, 8, 1, 32)
}
