package com.java.view;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.java.handler.output.Output;
import com.java.init.Setting;

public class Console {

	public static final void main(String[] args)
			throws SQLException, FileNotFoundException, IOException, InterruptedException {

		System.out.println("Please adjust date pattern in excel before run this program!");

		// Khởi tạo các dữ liệu cần thiết
		Setting.initData();

		String input = "";
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.print("db/file?  ");
			input = scanner.nextLine();
		} while (!input.equalsIgnoreCase("db") && !input.equalsIgnoreCase("file"));

		System.out.println("Program is running, please wait ...");

		if (input.equalsIgnoreCase("db")) {
			new Output().toDB();
		} else {
			new Output().toFile();
		}

		TimeUnit.SECONDS.sleep(5);

		scanner.close();

		System.out.println("========== See you again ==========");

	}
}
