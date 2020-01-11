//map是无序的，除了map.slice.func，其它的都可以
package main

import "fmt"

//声明map
func initMap() {
	m1 := map[string]string{
		"name":    "ccmouse",
		"course":  "golang",
		"site":    "imooc",
		"quality": "notbad",
	}

	m2 := make(map[string]int)

	var m3 map[string]int

	fmt.Printf("m1 %v, m2 %v, m3 %v \n", m1, m2, m3)
}

//遍历map
func iterMap() {
	m1 := map[string]string{
		"name":    "ccmouse",
		"course":  "golang",
		"site":    "imooc",
		"quality": "notbad",
	}
	for k, v := range m1 {
		fmt.Println(k, v)
	}
}

//通过key获取map的value
func getValueByKey() {
	m1 := map[string]string{
		"name":    "ccmouse",
		"course":  "golang",
		"site":    "imooc",
		"quality": "notbad",
	}

	//key存在
	value, ok := m1["name"]
	fmt.Println(value, ok)

	//key不存在
	if value, ok = m1["nama"]; ok {
		fmt.Println(value)
	} else {
		fmt.Println("key is not present")
	}
}

//删除
func deleteElemt() {
	m1 := map[string]string{
		"name":    "ccmouse",
		"course":  "golang",
		"site":    "imooc",
		"quality": "notbad",
	}
	fmt.Println(m1)

	delete(m1, "site")
	fmt.Println(m1)
}

func main() {
	initMap()
	iterMap()
	getValueByKey()
	deleteElemt()
}
