package com.java.handler.util;

public final class ExcelColumn {

	private ExcelColumn() {
	}

	// Hàm chuyển từ tên cột dạng String sang int (A -> 0, Z -> 26, AB -> 27,... )
	public static short toNumber(String name) {
		name = name.toUpperCase();
		short result = 0;
		for (int i = 0; i < name.length(); i++) {
			result = (short) (result * 26 + name.charAt(i) - 'A' + 1);
		}
		return (short) (result - 1);
	}

	/*
	 * Hàm chuyển từ số cột dạng int thành tên cột dạng String (VD: 0 -> A, 26 -> Z,
	 * 27 -> AB,... )
	 */
	public static String toString(int number) {
		StringBuilder sb = new StringBuilder();
		number++;
		while (number-- > 0) {
			sb.append((char) ('A' + (number % 26)));
			number = number / 26;
		}
		return sb.reverse().toString();
	}
}
