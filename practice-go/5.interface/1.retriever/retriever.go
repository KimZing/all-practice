//从外部使用者的角度来看的，看外部行为，而不是内部结构
package retriever

type Retriever interface {
	Get(url string) string
}
