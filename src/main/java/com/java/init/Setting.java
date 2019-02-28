package com.java.init;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.java.handler.util.ExcelColumn;

public final class Setting {

	private Setting() {
	}
	
	private static String urlDb;
	private static String userDb;
	private static String passwordDb;
	private static String user;
	private static String sourceFilePath;
	private static String sqlFilePath;
	private static String logFilePath;
	private static String tradingType;
	private static String databaseName;
	private static String tableName;
	private static String datePattern;
	private static String tradingDateCol;
	private static String traderIdCol;
	private static String traderNameCol;
	private static String partnerNameCol;
	private static String partnerAddCol01;
	private static String partnerAddCol02;
	private static String partnerAddCol03;
	private static String partnerAddCol04;
	private static String productDetailCol;
	private static String volumeCol;
	private static String unitCol;
	private static String unitPriceCol;
	private static String exchangeRateCol;
	private static String currencyCodeCol;

	public static void initData() throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		try (InputStream input = new FileInputStream(new File("data/config.properties"))) {
			prop.load(input);
			
			urlDb = prop.getProperty("urlDb");
			userDb = prop.getProperty("userDb");
			passwordDb = prop.getProperty("passwordDb");
			sourceFilePath = prop.getProperty("sourceFilePath");
			sqlFilePath = prop.getProperty("sqlFilePath");
			logFilePath = prop.getProperty("logFilePath");
			user = prop.getProperty("user");
			databaseName = prop.getProperty("databaseName");
			tableName = prop.getProperty("tableName");
			datePattern = prop.getProperty("datePattern");
			tradingDateCol = prop.getProperty("tradingDateCol");
			tradingType = prop.getProperty("tradingType");
			traderIdCol = prop.getProperty("traderIdCol");
			traderNameCol = prop.getProperty("traderNameCol");
			partnerNameCol = prop.getProperty("partnerNameCol");
			partnerAddCol01 = prop.getProperty("partnerAddCol01");
			partnerAddCol02 = prop.getProperty("partnerAddCol02");
			partnerAddCol03 = prop.getProperty("partnerAddCol03");
			partnerAddCol04 = prop.getProperty("partnerAddCol04");
			productDetailCol = prop.getProperty("productDetailCol");
			volumeCol = prop.getProperty("volumeCol");
			unitCol = prop.getProperty("unitCol");
			unitPriceCol = prop.getProperty("unitPriceCol");
			exchangeRateCol = prop.getProperty("exchangeRateCol");
			currencyCodeCol = prop.getProperty("currencyCodeCol");
		}
	}

	public static String getUrlDb() {
		return urlDb;
	}

	public static String getUserDb() {
		return userDb;
	}

	public static String getPasswordDb() {
		return passwordDb;
	}

	public static String getSourceFilePath() {
		return sourceFilePath;
	}

	public static String getSqlFilePath() {
		return sqlFilePath;
	}

	public static String getLogFilePath() {
		return logFilePath;
	}

	public static String getUser() {
		return user;
	}

	public static String getDatabaseName() {
		return databaseName;
	}

	public static String getTableName() {
		return tableName;
	}

	public static String getDatePattern() {
		return datePattern;
	}

	public static String getTradingType() {
		return tradingType;
	}

	public static short getCompanyIdCol() {
		if (StringUtils.isNumeric(traderIdCol))
			return Short.parseShort(traderIdCol);
		return ExcelColumn.toNumber(traderIdCol);
	}

	public static short getTraderIdCol() {
		if (StringUtils.isNumeric(traderIdCol))
			return Short.parseShort(traderIdCol);
		return ExcelColumn.toNumber(traderIdCol);
	}

	public static short getTraderNameCol() {
		if (StringUtils.isNumeric(traderNameCol))
			return Short.parseShort(traderNameCol);
		return ExcelColumn.toNumber(traderNameCol);
	}

	public static short getTradingDateCol() {
		if (StringUtils.isNumeric(tradingDateCol))
			return Short.parseShort(tradingDateCol);
		return ExcelColumn.toNumber(tradingDateCol);
	}

	public static short getPartnerNameCol() {
		if (StringUtils.isNumeric(partnerNameCol))
			return Short.parseShort(partnerNameCol);
		return ExcelColumn.toNumber(partnerNameCol);
	}

	public static short getPartnerAddCol01() {
		if (StringUtils.isNumeric(partnerAddCol01))
			return Short.parseShort(partnerAddCol01);
		return ExcelColumn.toNumber(partnerAddCol01);
	}

	public static short getPartnerAddCol02() {
		if (StringUtils.isNumeric(partnerAddCol02))
			return Short.parseShort(partnerAddCol02);
		return ExcelColumn.toNumber(partnerAddCol02);
	}

	public static short getPartnerAddCol03() {
		if (StringUtils.isNumeric(partnerAddCol03))
			return Short.parseShort(partnerAddCol03);
		return ExcelColumn.toNumber(partnerAddCol03);
	}

	public static short getPartnerAddCol04() {
		if (StringUtils.isNumeric(partnerAddCol04))
			return Short.parseShort(partnerAddCol04);
		return ExcelColumn.toNumber(partnerAddCol04);
	}

	public static short getProductDetailCol() {
		if (StringUtils.isNumeric(productDetailCol))
			return Short.parseShort(productDetailCol);
		return ExcelColumn.toNumber(productDetailCol);
	}

	public static short getVolumeCol() {
		if (StringUtils.isNumeric(volumeCol))
			return Short.parseShort(volumeCol);
		return ExcelColumn.toNumber(volumeCol);
	}

	public static short getUnitCol() {
		if (StringUtils.isNumeric(unitCol))
			return Short.parseShort(unitCol);
		return ExcelColumn.toNumber(unitCol);
	}

	public static short getUnitPriceCol() {
		if (StringUtils.isNumeric(unitPriceCol))
			return Short.parseShort(unitPriceCol);
		return ExcelColumn.toNumber(unitPriceCol);
	}

	public static short getExchangeRateCol() {
		if (StringUtils.isNumeric(exchangeRateCol))
			return Short.parseShort(exchangeRateCol);
		return ExcelColumn.toNumber(exchangeRateCol);
	}

	public static short getCurrencyCodeCol() {
		if (StringUtils.isNumeric(currencyCodeCol))
			return Short.parseShort(currencyCodeCol);
		return ExcelColumn.toNumber(currencyCodeCol);
	}
}