package main

import (
	"database/sql"
	"fmt"
	"time"

	_ "github.com/go-sql-driver/mysql"
)

func main() {
	db, err := sql.Open("mysql", "root:123456@/test?charset=utf8")
	checkErr(err)

	//插入数据
	stmt, err := db.Prepare("INSERT INTO userinfo(username, departname, created) VALUES (?, ?, ?)")
	checkErr(err)

	res, err := stmt.Exec("kingboy", "maxrocky", time.Now())
	id, err := res.LastInsertId()
	count, err := res.RowsAffected()
	fmt.Printf("id is %d, affect %d rows \n", id, count)

	//更新数据
	stmt, err = db.Prepare("UPDATE userinfo SET username = ?, departname = ? WHERE uid = ?")
	checkErr(err)
	res, err = stmt.Exec("king", "aaa", 4)
	count, err = res.RowsAffected()
	fmt.Printf("affect rows is %d \n", count)

	//查询数据
	rows, err := db.Query("SELECT  *  FROM userinfo")
	for rows.Next() {
		var uid int
		var username string
		var departname string
		var created time.Time
		rows.Scan(&uid, &username, &departname, &created)
		fmt.Printf("uid is %d, username is %s, departname is %s, create time is %v\n", uid, username, departname, created)
	}

	//删除数据
	stmt, err = db.Prepare("DELETE FROM userinfo WHERE uid > ?")
	res, err = stmt.Exec(5)
	count, err = res.RowsAffected()
	fmt.Println(count)

}

func checkErr(err error) {
	if err != nil {
		panic(err)
	}
}

/*
CREATE TABLE `userinfo` (
	`uid` INT(10) NOT NULL AUTO_INCREMENT,
	`username` VARCHAR(64) NULL DEFAULT NULL,
	`departname` VARCHAR(64) NULL DEFAULT NULL,
	`created` DATE NULL DEFAULT NULL,
	PRIMARY KEY (`uid`)
);

CREATE TABLE `userdetail` (
	`uid` INT(10) NOT NULL DEFAULT '0',
	`intro` TEXT NULL,
	`profile` TEXT NULL,
	PRIMARY KEY (`uid`)
)
*/
