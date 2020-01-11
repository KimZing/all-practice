package main

import (
	"fmt"
	"io/ioutil"
	"net/http"
	"regexp"
)

//匹配IP
func IsIP(ip string) (bool, error) {
	return regexp.MatchString("^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$", ip)
}

func isNum(str string) {
	if len := len(str); len <= 0 {
		fmt.Println("input string is empty")
	} else if isNum, _ := regexp.MatchString("^[0-9]+$", str); isNum {
		fmt.Println("input string is number")
	} else {
		fmt.Println("input string is not number")
	}
}

func httpContent(url string) {
	res, err := http.Get(url)
	if err != nil {
		fmt.Println(err)
	}
	defer res.Body.Close()

	bytes, _ := ioutil.ReadAll(res.Body)
	s := string(bytes)

	compile, _ := regexp.Compile("\\<[\\S\\s]+?\\>")
	compile.ReplaceAllString(s, "")

}

func regexFind() {
	s := "i am learning go language"
	b := []byte(s)
	//查找由小写字母组成，成都为4到8的字符
	r1, _ := regexp.Compile("[a-z]{4,8}")
	f1 := r1.Find(b)
	fmt.Println(string(f1))

	//查找符合正则的全部字符串
	all := r1.FindAll(b, -1)
	fmt.Println(len(all))

	//查找符合条件的位置
	index := r1.FindIndex(b)
	fmt.Println(index)

	//查找符合条件的所有位置
	index2 := r1.FindAllIndex(b, -1)
	fmt.Println(index2)

	//submatch
	r2, _ := regexp.Compile("am(.*)lang(.*)")
	//查找Submatch,返回数组，第一个元素是匹配的全部元素，第二个元素是第一个()里面的，第三个是第二个()里面的
	//下面的输出第一个元素是"am learning Go language"
	//第二个元素是" learning Go "，注意包含空格的输出
	//第三个元素是"uage"
	submatch := r2.FindSubmatch(b)
	fmt.Println(len(submatch))
	for _, v := range submatch {
		fmt.Println(string(v))
	}

	//定义和上面的FindIndex一样
	submatchindex := r2.FindSubmatchIndex(b)
	fmt.Println(submatchindex)

	//FindAllSubmatch,查找所有符合条件的子匹配
	submatchall := r2.FindAllSubmatch(b, -1)
	fmt.Println(submatchall)

	//FindAllSubmatchIndex,查找所有字匹配的index
	submatchallindex := r2.FindAllSubmatchIndex(b, -1)
	fmt.Println(submatchallindex)
}

func expend() {
	src := []byte(`
		call hello alice
		hello bob
		call hello eve
	`)
	pat := regexp.MustCompile(`(?m)(call)\s+(?P<cmd>\w+)\s+(?P<arg>.+)\s*$`)
	res := []byte{}
	for _, s := range pat.FindAllSubmatchIndex(src, -1) {
		res = pat.Expand(res, []byte("$cmd('$arg')\n"), src, s)
	}
	fmt.Println(string(res))
}

func main() {
	s := "1.1.1.1"
	b, e := IsIP(s)
	if e != nil {
		fmt.Println(e)
	}
	fmt.Println(b)

	isNum("123123")

	//httpContent("http://www.baidu.com")

	regexFind()

	expend()
}
