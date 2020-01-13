package main

import (
	"fmt"
	"practice-go/5.interface/1.retriever"
	"practice-go/5.interface/2.mock"
	"practice-go/5.interface/3.real"
	"time"
)

func main() {

	//复习
	//可以存储值， 也可以存储指针
	var retiever1 retriever.Retriever
	retiever1 = mock.Retriever{"mock content"}
	fmt.Printf("%T %V \n", retiever1, retiever1)
	retiever1 = &mock.Retriever{"mock content pionter"}
	fmt.Printf("%T %V \n", retiever1, retiever1)

	//========================接口的基本使用=========================
	//mock
	var retriever retriever.Retriever
	retriever = mock.Retriever{"Hello"}
	fmt.Println(retriever.Get("http://www.imooc.com"))
	//看看retriever这个接口中究竟存了啥,这里存了实现者的类型和值(值可以是真实的值，也可以是指针的值)，这里是对象的值
	fmt.Printf("%T %v \n", retriever, retriever)
	inspect(retriever)

	//real
	retriever = &real.Retriever{
		UserAgent: "Mozilla/5.0",
		TimeOut:   time.Second,
	}
	fmt.Println(retriever.Get("http://www.imooc.com"))

	//========================接口的内部存储=========================
	//看看retriever这个接口中究竟存了啥,这里存了实现者的类型和值(值可以是真实的值，也可以是指针的值)，这里是对象的指针
	fmt.Printf("%T %v \n", retriever, retriever)

	//因为接口中可以存储指针，所以我们一般不需要使用接口的指针

	//========================判断类型=========================
	//那么接口中存的是指针，我们如何知道接口真正指向的类型呢？
	//方式一：Type Switch
	inspect(retriever)
	//方式二：Type Assertion
	//非常规写法
	realRetriever := retriever.(*real.Retriever)
	fmt.Println(realRetriever.TimeOut)
	//标准写法
	if mockRetriever, ok := retriever.(mock.Retriever); ok {
		fmt.Println("Contents:", mockRetriever.Content)
	} else {
		fmt.Println("retriever is not a mockRetriever")
	}

	retriever = &mock.Retriever{"ptr"}
	contents := retriever.Get("http://www.baidu.com")
	inspect(retriever) //使用了指针，所以类型判断中不会有打印
	fmt.Println(contents)

	//========================任意类型=========================
	//interface{}可以表示任何类型
	printAny(1)
	printAny("hello")
	printAny(retriever)



}

func printAny(v interface{}) {
	fmt.Println(v)
}

func inspect(retriever retriever.Retriever) {
	switch t := retriever.(type) {
	case mock.Retriever:
		fmt.Println("Contents:", t.Content)
	case *real.Retriever:
		fmt.Println("UserAgent:", t.UserAgent)
	}
}
