package main

import (
	"fmt"
	"os"
)

func readMaze(path string) [][]int {
	file, err := os.Open(path)
	errHandler(err)
	var row, col int
	fmt.Fscanf(file, "%d %d", &row, &col)
	maze := make([][]int, row)
	for i := range maze {
		maze[i] = make([]int, col)
		for j := range maze[i] {
			fmt.Fscanf(file, "%d", &maze[i][j])
		}
	}
	return maze
}

func errHandler(err error) {
	if err != nil {
		panic(err)
	}
}

func main() {
	maze := readMaze("13.algorithm/breadthfirst/maze.in")
	fmt.Println(maze)

}
