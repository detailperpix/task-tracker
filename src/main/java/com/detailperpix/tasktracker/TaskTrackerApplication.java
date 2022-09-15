package com.detailperpix.tasktracker;

import com.detailperpix.tasktracker.services.SQLiteDatabase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskTrackerApplication {

	public static void main(String[] args) {
		SQLiteDatabase.createDatabase("mydb.db");
		SpringApplication.run(TaskTrackerApplication.class, args);
	}

}
