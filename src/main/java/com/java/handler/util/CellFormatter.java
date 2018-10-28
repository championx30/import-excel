package com.java.handler.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;

import com.java.init.Setting;

public class CellFormatter {

	public static Float getFloatCell(Cell cell) {
		DataFormatter fmt = new DataFormatter();
		fmt.addFormat("General", new java.text.DecimalFormat("#.###########"));
		if (cell == null)
			return null;
		switch (cell.getCellTypeEnum()) {
		case NUMERIC:
			return Float.parseFloat(fmt.formatCellValue(cell));
		case STRING:
			if (StringUtils.isBlank(fmt.formatCellValue(cell)))
				return null;
			return Float.parseFloat(fmt.formatCellValue(cell).trim());
		default:
			break;
		}
		return null;
	}

	public static Integer getIntegerCell(Cell cell) {
		DataFormatter fmt = new DataFormatter();
		fmt.addFormat("General", new java.text.DecimalFormat("#"));
		if (cell == null)
			return null;
		switch (cell.getCellTypeEnum()) {
		case NUMERIC:
			return Integer.parseInt(fmt.formatCellValue(cell));
		case STRING:
			if (StringUtils.isBlank(fmt.formatCellValue(cell)))
				return null;
			return Integer.parseInt(fmt.formatCellValue(cell).trim());
		default:
			break;
		}
		return null;
	}

	public static String getStringCell(Cell cell) {
		DataFormatter fmt = new DataFormatter();
		fmt.addFormat("Number", new java.text.DecimalFormat("#"));
		if (cell == null)
			return null;
		switch (cell.getCellTypeEnum()) {
		case STRING:
			return cell.getStringCellValue().trim();
		case NUMERIC:
			return fmt.formatCellValue(cell);
		default:
			break;
		}
		return null;
	}

	public static String getDateCell(Cell cell) {
		if (cell == null)
			return null;
		String pattern = "dd-MMM-yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(Setting.getDatePattern());
		try {
			String rawData = cell.getStringCellValue();
			Date date = sdf.parse(rawData);
			sdf.applyPattern(pattern);
			return sdf.format(date);
		} catch (ParseException e) {
			System.out.println("Bạn nhập sai định dạng Date");
		}
		return null;
	}
}
