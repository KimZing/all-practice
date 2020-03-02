package window

import (
	"fyne.io/fyne/app"
	"fyne.io/fyne/widget"
)

func NewApp() {
	application := app.New()
	window := application.NewWindow("KimSync")
	window.SetContent(
		widget.NewVBox(
			widget.NewLabel("Hello Fyne!"),
			widget.NewButton("Quit", func() {
				application.Quit()
			}),
		),
	)
	window.ShowAndRun()
}
