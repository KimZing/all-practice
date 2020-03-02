package data

import (
	"os"
	"time"
)

type Source struct {
	file *os.File
	Name string
	ByteSize int
	modifyTime time.Time
}