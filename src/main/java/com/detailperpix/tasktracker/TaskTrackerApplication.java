package com.detailperpix.tasktracker;

import com.detailperpix.tasktracker.services.SQLiteDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class TaskTrackerApplication {

	public static void main(String[] args) {
		try {

			SQLiteDatabase.createAndUseDatabase("mydb.db");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		SpringApplication.run(TaskTrackerApplication.class, args);
	}

}
