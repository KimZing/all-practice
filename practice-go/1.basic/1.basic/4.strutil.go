//字符串操作类及操作
package main

import (
	"fmt"
	"strconv"
	"strings"
)

func main() {
	//strings
	fmt.Println(strings.Compare("a", "b"))
	fmt.Println(strings.Contains("i am kingboy", "king"))
	fmt.Println(strings.Join([]string{"a", "b"}, ","))
	fmt.Println(strings.Trim("  Hello World   ", " "))
	fmt.Println(strings.Count("aaaa", "a"))
	fmt.Println(strings.Fields("i am kingboy"))
	fmt.Println(strings.Index("i am kingboy", "am"))

	//strconv
	bytes := make([]byte, 0, 100)
	bytes = strconv.AppendBool(bytes, true)
	bytes = strconv.AppendInt(bytes, 4567, 10)
	bytes = strconv.AppendQuote(bytes, "abcdefg")
	fmt.Println(string(bytes))

	a := strconv.FormatBool(false)
	b := strconv.FormatFloat(123.23, 'g', 12, 64)
	c := strconv.FormatInt(1234, 10)
	d := strconv.FormatUint(12345, 10)
	// int to ascii
	e := strconv.Itoa(1023)
	fmt.Println(a, b, c, d)

	aa, _ := strconv.ParseBool("false")
	bb, _ := strconv.ParseFloat("123.23", 64)
	cc, _ := strconv.ParseInt("1234", 10, 64)
	dd, _ := strconv.ParseUint("12345", 10, 64)
	// ascii to int
	ee, _ := strconv.Atoi("1023")
	fmt.Println(aa, bb, cc, dd, ee)

	//结果仍然是int64，但是可以转换为int32,但是如果超出大小，会报错
	fmt.Println(strconv.ParseInt("9223372036854775807", 10, 16))
}
