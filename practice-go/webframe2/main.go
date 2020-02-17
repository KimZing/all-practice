package main

import (
	"net/http"
	kee "practice-go/webframe2/kee"
	"time"
)

func main() {
	kee := kee.New()
	kee.GET("/index", indexFunc)
	kee.GET("/now", timeFunc)
	kee.GET("/json", jsonFunc)
	kee.Run(":8080")

}

func jsonFunc(ctx *kee.Context) {
	ctx.JSON(http.StatusOK, []int{2, 4})
}

func timeFunc(context *kee.Context) {
	context.HTML(http.StatusOK, time.Now().Local().String())
}

func indexFunc(context *kee.Context) {
	context.HTML(http.StatusOK, "welcome to kee frame with context and router!")
}