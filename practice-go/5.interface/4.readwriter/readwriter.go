package main

import "fmt"

//1.定义一个reader
type reader interface {
	read(path string)
}

//2.定义一个writer
type writer interface {
	write(path string)
}

//3.定义一个readerwriter
type readerwriter interface {
	reader
	writer
}

type fileOperation struct{}

func (f *fileOperation) read(path string) {
	fmt.Println("reading ", path)
}

func (f *fileOperation) write(path string) {
	fmt.Println("writing ", path)
}

func main() {
	//我们实现了一个读接口
	var reader reader
	reader = &fileOperation{}
	reader.read("~/go")

	//我们实现一个写接口
	var writer writer
	writer = &fileOperation{}
	writer.write("~/java")

	//这个时候我们想要实现一个既可以写又可以读的接口，只需要定义一个readerwriter接口即可，只要实现者有这两个个方法，就可以使用
	//实现者不需要关心自己实现了哪些接口，只要写方法就可以。而实现的接口由使用者定义
	var readerwriter readerwriter
	readerwriter = &fileOperation{}
	readerwriter.read("~/go")
	readerwriter.write("~/java")

	//也就是说，接口的定义完全在于使用者，可以自由组合
	//类比java，java中需要具体类去实现这个接口才可以

}
