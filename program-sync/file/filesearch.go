package file

import (
	"fmt"
	"io/ioutil"
	"log"
)

func find(path string) {
	dirs, err := ioutil.ReadDir(path)
	if err != nil {
		log.Fatal(err)
	}
	for _, dir := range dirs {
		fmt.Println(dir.Name())
	}
}

