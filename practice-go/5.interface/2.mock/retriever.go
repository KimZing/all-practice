package mock

type Retriever struct {
	Content string
}

func (retriever Retriever) Get(url string) string {
	return url + " " + retriever.Content
}
