package com.java.handler.util;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public final class DataReader {

	private DataReader() {
	}

	public static boolean isTaxCode(String taxCode) {
		if ("9999999999998".equals(taxCode))
			return false;
		return true;
	}

	public static String getTaxCode(String taxCode) {
		StringBuilder str = new StringBuilder();
		if (StringUtils.isBlank(taxCode))
			return null;
		switch (taxCode.length()) {
		case 7:
			str.append("0");
		case 8:
			str.append("0");
		case 9:
			str.append("0");
		case 10:
			return str.append(taxCode).toString();
		case 11:
			str.append("0");
		case 12:
			str.append("0");
		case 13:
			return str.append(taxCode).substring(0, 13);
		}
		return null;
	}

	public static String getCompanyName(String str) {
		str = str.toLowerCase();
		str = replaceSpecialChar(str);
		str = replaceString(str);
		// Bỏ các từ trong ngoặc bao gồm cặp dấu ngoặc
		if (str.contains("(") && str.contains(")")) {
			str = str.substring(0, str.indexOf("(")) + str.substring(str.indexOf(")") + 1, str.length());
		}
		StringBuilder result = new StringBuilder();
		String[] arr = str.split("\\s+");
		// List các từ viết hoa
		List<String> listUpperCase = Arrays.asList("&", ".", "cn", "dv", "hh", "hhcn", "kcn", "tm", "tmdv", "tnhh",
				"vn", "xnk");
		for (String word : arr) {
			// Viết hoa các từ nếu thuộc listUpperCase ở trên
			for (String s : listUpperCase) {
				if (word.contains(s))
					word = word.toUpperCase();
			}
			if (word.isEmpty()) {
				continue;
			}
			// Viết hoa chữ cái đầu của các từ
			result.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1)).append(" ");
		}
		return result.toString().trim();
	}

	public static String getTraderType(String str) {
		str = str.toLowerCase();
		List<String> listType = Arrays.asList("chi nhánh", "công ty", "cơ sở", "cửa hàng", "doanh nghiệp", "hợp tác xã",
				"ltd", "nhà máy", "văn phòng đại diện", "xí nghiệp");
		for (String list : listType) {
			if (str.contains(list)) {
				return "COMPANY";
			}
		}
		return "PERSONAL";
	}

	public static String getCompanyAdd(String str) {
		str = replaceSpecialChar(str);
		StringBuilder result = new StringBuilder();
		String[] arr = str.split("\\s+");
		for (String word : arr) {
			result.append(word).append(" ");
		}
		return result.toString().trim();
	}

	public static String getPartnerCountry(String partnerAdd1, String partnerAdd2, String partnerAdd3,
			String partnerAdd4) {
		if (StringUtils.isBlank(partnerAdd1) && StringUtils.isBlank(partnerAdd2) && StringUtils.isBlank(partnerAdd3)
				&& StringUtils.isBlank(partnerAdd4))
			return null;
		StringBuilder result = new StringBuilder();
		if (!StringUtils.isBlank(partnerAdd1)) {
			result.append(partnerAdd1);
		}
		if (!StringUtils.isBlank(partnerAdd2)) {
			result.append(",").append(partnerAdd2);
		}
		if (!StringUtils.isBlank(partnerAdd3)) {
			result.append(",").append(partnerAdd3);
		}
		if (!StringUtils.isBlank(partnerAdd4)) {
			result.append(",").append(partnerAdd4);
		}
		return replaceSpecialChar(result.toString());
	}

	public static String getProductDetail(String str) {
		str = replaceSpecialChar(str);
		String split = "#&";
		while (str.contains(split)) {
			int index = str.indexOf(split);
//			Nếu vị trí split nằm bên trái chuỗi sẽ chỉ lấy chuỗi từ sau vị trí split đến cuối
			if (index <= str.length() / 2)
				str = str.substring(index + split.length(), str.length());
//			Ngược lại sẽ lấy từ đầu đến trước vị trí split
			else
				str = str.substring(0, index);
		}
		str = str.substring(0, 1).toUpperCase() + str.substring(1);
		return str;
	}

	public static String getProductType(String str) {
		if (StringUtils.isBlank(str))
			return null;
		str = str.toLowerCase();
		if (str.contains("sắt")) {
			if (str.contains("phế liệu"))
				return "Sắt phế liệu";
			if (str.contains("thép"))
				return "Sắt thép";
			return "Sắt";
		}
		List<String> listOfSteel = Arrays.asList("làm dao", "tròn", "phế liệu", "khớp nối", "tấm", "dây", "đặc chủng",
				"hình", "hộp", "inox", "lá", "mã kẽm", "mạ kẽm", "ống", "phôi", "phế liệu", "thanh", "xây dựng", "cuộn",
				"cán", "không hợp kim", "hợp kim");
		StringBuilder type = new StringBuilder();
		for (String s : listOfSteel) {
			if (str.contains(s))
				return type.append("Thép ").append(s).toString();
		}
		if (str.contains("thép") || str.contains("thep"))
			return "Thép";
		return "Kim loại khác";
	}

	public static String replaceString(String str) {
		if (StringUtils.isBlank(str))
			return str;
		if (str.length() > 2 && str.substring(0, 2).equals("cn")) {
			str = str.replaceFirst("cn", "chi nhánh");
		}
		str = str.replaceAll("\\s*ctcp\\s*", " công ty cổ phần ");
		str = str.replaceAll("c\\s*ty", " công ty ");
		str = str.replaceAll(" cp ", " cổ phần ");

		str = str.replaceAll("\\s*dntn\\s*", " doanh nghiệp tư nhân ");
		str = str.replaceAll("\\s*htx\\s*", " hợp tác xã ");
		str = str.replaceAll("\\s*trách nhiệm hữu hạn\\s*", " tnhh ");

		str = str.replaceAll(" cn ", " công nghiệp ");
		str = str.replace(" tn ", " tư nhân ");
		return str;
	}

	public static String replaceSpecialChar(String str) {
		if (StringUtils.isBlank(str))
			return str;
		str = str.replaceAll("\\s*-\\s*", " - ");
		str = str.replaceAll("\\s*:", ": ");
		str = str.replace(",,", ",");
		str = str.replaceAll("\\s*,", ", ");
		str = str.replaceAll("\\(\\s*", " (");
		str = str.replaceAll("\\s*\\)", ") ");
		str = str.replaceAll("\\)\\s*,", "),");
		str = str.replaceAll("\\s+", " ");
		return str;
	}
}