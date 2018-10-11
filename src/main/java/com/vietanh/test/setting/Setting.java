package com.vietanh.test.setting;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.vietanh.test.util.ExcelColumn;

public final class Setting {

	private Setting() {
	}

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

	private static String partnerAddCol1;
	private static String partnerAddCol2;
	private static String partnerAddCol3;
	private static String partnerAddCol4;

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
			partnerAddCol1 = prop.getProperty("partnerAddCol1");
			partnerAddCol2 = prop.getProperty("partnerAddCol2");
			partnerAddCol3 = prop.getProperty("partnerAddCol3");
			partnerAddCol4 = prop.getProperty("partnerAddCol4");
			productDetailCol = prop.getProperty("productDetailCol");
			volumeCol = prop.getProperty("volumeCol");
			unitCol = prop.getProperty("unitCol");
			unitPriceCol = prop.getProperty("unitPriceCol");
			exchangeRateCol = prop.getProperty("exchangeRateCol");
			currencyCodeCol = prop.getProperty("currencyCodeCol");
		}
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

	public static short getPartnerAddCol1() {
		if (StringUtils.isNumeric(partnerAddCol1))
			return Short.parseShort(partnerAddCol1);
		return ExcelColumn.toNumber(partnerAddCol1);
	}

	public static short getPartnerAddCol2() {
		if (StringUtils.isNumeric(partnerAddCol2))
			return Short.parseShort(partnerAddCol2);
		return ExcelColumn.toNumber(partnerAddCol2);
	}

	public static short getPartnerAddCol3() {
		if (StringUtils.isNumeric(partnerAddCol3))
			return Short.parseShort(partnerAddCol3);
		return ExcelColumn.toNumber(partnerAddCol3);
	}

	public static short getPartnerAddCol4() {
		if (StringUtils.isNumeric(partnerAddCol4))
			return Short.parseShort(partnerAddCol4);
		return ExcelColumn.toNumber(partnerAddCol4);
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