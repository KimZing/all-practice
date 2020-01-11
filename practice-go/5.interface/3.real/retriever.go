package real

import (
	"net/http"
	"net/http/httputil"
	"time"
)

type Retriever struct {
	UserAgent string
	TimeOut   time.Duration
}

func (retriever *Retriever) Get(url string) string {
	resp, err := http.Get(url)
	defer resp.Body.Close()
	if err != nil {
		panic(err)
	}

	result, e := httputil.DumpResponse(resp, true)
	if err != nil {
		panic(e)
	}

	return string(result)
}
