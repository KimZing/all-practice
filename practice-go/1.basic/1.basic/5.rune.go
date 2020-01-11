package main

import (
	"fmt"
	"unicode/utf8"
)

//字符串是由字节组成的，可以转成[]byte,rune在go中是相当于java中的char，所以可以转化为rune

func main() {
	word := "Hello 小金!"
	fmt.Println(word)

	//长度:UTF-8是可变编码，英文一个长度，汉字三个长度
	fmt.Println(len(word))
	//看看转换为原始数组的长度,共用了13个字节来存储
	bytes := []byte(word)
	for _, b := range bytes {
		fmt.Printf("%X ", b)
	}

	fmt.Println()

	//如果我们想获取长度，可以使用go提供的utf8来操作
	count := utf8.RuneCountInString(word)
	fmt.Println(count)
	//打印每个字符
	bytes = []byte(word)
	for len(bytes) > 0 {
		r, size := utf8.DecodeRune(bytes)
		fmt.Printf("%c ", r)
		bytes = bytes[size:]
	}

	fmt.Println()



}
