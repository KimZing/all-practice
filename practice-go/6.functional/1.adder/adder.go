package adder

func Add() func(x int) int {
	//自由变量
	sum := 0
	return func(v int) int {
		//v 内部变量，局部变量
		sum += v
		return sum
	}
}

type radd func(x int) (int, radd)

func Add2(base int) radd {
	return func(v int) (int, radd) {
		return base + v, Add2(base + v)
	}
}
