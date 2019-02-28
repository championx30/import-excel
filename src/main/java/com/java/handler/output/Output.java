package com.java.handler.output;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.java.handler.connect.Config;
import com.java.handler.log.Log;
import com.java.handler.util.CellFormatter;
import com.java.handler.util.DataReader;
import com.java.init.Setting;
import com.monitorjbl.xlsx.StreamingReader;

public class Output {

	private String taxCode;
	private String traderName;
	private String traderType;
	private String tradingDate;
	private String partnerName;
//	private String partnerCountry;
	private String partnerAddCol01;
	private String partnerAddCol02;
	private String partnerAddCol03;
	private String partnerAddCol04;
	private String productDetail;
	private String productType;
	private Float volume;
	private String unit;
	private Float unitPrice;
	private Float exchangeRate;
	private String currencyCode;
	private Float value;
	private int countRowRead = 0, countBlankTrader = 0, countTaxCodeIsNull = 0, countRecordDb = 0, countRecordFile = 0;

	public Output() {
	}

	public void toDB() {
		long start = System.currentTimeMillis();

		String sqlInsert = "INSERT INTO " + Setting.getDatabaseName() + "." + Setting.getTableName()
			+ "(TAX_CODE, TRADER_NAME, TRADER_TYPE, TRADING_DATE, TRADING_TYPE, PARTNER_NAME,"
			+ "PARTNER_ADDRESS01, PARTNER_ADDRESS02, PARTNER_ADDRESS03, PARTNER_ADDRESS04, PRODUCT_TYPE,"
			+ "PRODUCT_DETAIL, VOLUME, UNIT, UNIT_PRICE, EXCHANGE_RATE, CURRENCY_CODE, VALUE, CREATED_DATE, CREATED_BY) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_DATE, ?)";
		System.out.println("Opening the file: " + Setting.getSourceFilePath());
		try (FileInputStream fis = new FileInputStream(new File(Setting.getSourceFilePath()));
				Workbook workbook = StreamingReader.builder().rowCacheSize(100).open(fis);
				Connection conn = new Config().getJDBCConnection();
				PreparedStatement ps = conn.prepareStatement(sqlInsert)) {
			
			conn.setAutoCommit(false);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = sheet.iterator();
//			Bỏ qua row đầu tiên
			iterator.next();
			while (iterator.hasNext()) {
				countRowRead++;
				Row currentRow = iterator.next();
				if (currentRow == null || currentRow.getCell(Setting.getTraderNameCol()) == null) {
					countBlankTrader++;
					continue;
				}
				taxCode = CellFormatter.getStringCell(currentRow.getCell(Setting.getTraderIdCol()));
				if (!DataReader.isTaxCode(taxCode)) {
					countTaxCodeIsNull++;
					continue;
				}
				readData(currentRow);
				ps.setString(1, taxCode);
				ps.setString(2, traderName);
				// ps.setString(3, null); // finfo Id của trader
				ps.setString(3, traderType);
				ps.setString(4, tradingDate);
				ps.setString(5, Setting.getTradingType()); // Loại xuất khẩu hay nhập khẩu (IMPORT/EXPORT)
				ps.setString(6, partnerName);
//				ps.setString(8, partnerCountry);
				ps.setString(7, partnerAddCol01);
				ps.setString(8, partnerAddCol02);
				ps.setString(9, partnerAddCol03);
				ps.setString(10, partnerAddCol04);
				ps.setString(11, productType);
				ps.setString(12, productDetail);
				ps.setObject(13, volume);
				ps.setString(14, unit);
				ps.setObject(15, unitPrice);
				ps.setObject(16, exchangeRate);
				ps.setString(17, currencyCode);
				ps.setObject(18, value);
				ps.setString(19, Setting.getUser());
				ps.addBatch();
				if (++countRecordDb % 5000 == 0) {
					System.out.println(countRecordDb + " records were added to the batch");
				}
			}
			System.out.println("Executing the Batch");
			ps.executeBatch();
			conn.commit();
			long finish = System.currentTimeMillis();
			new Log().write(start, finish, countRowRead, countBlankTrader, countTaxCodeIsNull, countRecordDb,
					countRecordFile);
			System.out.println("Successfully! \nOpen " + Setting.getLogFilePath() + " for more details.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("Steel File not found!");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Processing has failed!");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("SQL Exception! Processing has failed!");
		}
	}

	public void toFile() {
		long start = System.currentTimeMillis();

		System.out.println("Opening the file: " + Setting.getSourceFilePath());
		try (FileInputStream fis = new FileInputStream(new File(Setting.getSourceFilePath()));
				Workbook workbook = StreamingReader.builder().rowCacheSize(100).open(fis);) {

			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = sheet.iterator();
			StringBuilder result = new StringBuilder();
//			Bỏ qua row đầu tiên
			iterator.next();
			while (iterator.hasNext()) {
				countRowRead++;
				Row currentRow = iterator.next();
				if (currentRow == null || currentRow.getCell(Setting.getTraderNameCol()) == null) {
					countBlankTrader++;
					continue;
				}
				taxCode = CellFormatter.getStringCell(currentRow.getCell(Setting.getTraderIdCol()));
				if (!DataReader.isTaxCode(taxCode)) {
					countTaxCodeIsNull++;
					continue;
				}
				readData(currentRow);
				String sqlInsert = "INSERT INTO " + Setting.getDatabaseName() + "." + Setting.getTableName()
					+ "(TAX_CODE, TRADER_NAME, TRADER_TYPE, TRADING_DATE, TRADING_TYPE, PARTNER_NAME,"
					+ "PARTNER_ADDRESS01, PARTNER_ADDRESS02, PARTNER_ADDRESS03, PARTNER_ADDRESS04, PRODUCT_TYPE,"
					+ "PRODUCT_DETAIL, VOLUME, UNIT, UNIT_PRICE, EXCHANGE_RATE, CURRENCY_CODE, VALUE, CREATED_DATE, CREATED_BY) "
					+ "VALUES (" + taxCode + ", '" + traderName.replace("'", "`") + "', '" + traderType + "', '"
					+ tradingDate + "', '" + Setting.getTradingType() + "', '" + partnerName.replace("'", "`")
					+ "', '" + partnerAddCol01.replace("'", "`") + "', '" + partnerAddCol02.replace("'", "`") + "', '" 
					+ partnerAddCol03.replace("'", "`") + "', '" + partnerAddCol04.replace("'", "`") + "', '"
					+ productType + "', '" + productDetail.replace("'", "`") + "', " + volume + ", '" + unit + "', " + unitPrice + ", "
					+ exchangeRate + ", '" + currencyCode + "', " + value + ", CURRENT_DATE, '" + Setting.getUser() + "');\n";
				result.append(sqlInsert);
				countRecordFile++;
			}
			writeFile(result.toString());
			long finish = System.currentTimeMillis();
			System.out.println("Saved query statements into " + Setting.getSqlFilePath());
			new Log().write(start, finish, countRowRead, countBlankTrader, countTaxCodeIsNull, countRecordDb,
					countRecordFile);
			System.out.println("Successfully! \nOpen " + Setting.getLogFilePath() + " for more details.");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("Steel File not found!");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Processing has failed!");
		}
	}

	public void readData(Row row) {
		taxCode = DataReader.getTaxCode(taxCode);
		traderName = DataReader.getCompanyName(CellFormatter.getStringCell(row.getCell(Setting.getTraderNameCol())));
		traderType = DataReader.getTraderType(traderName);
		tradingDate = CellFormatter.getDateCell(row.getCell(Setting.getTradingDateCol()));
		partnerName = DataReader
				.replaceSpecialChar(CellFormatter.getStringCell(row.getCell(Setting.getPartnerNameCol())));
//		partnerCountry = DataReader.getPartnerCountry(
//				CellFormatter.getStringCell(row.getCell(Setting.getPartnerAddCol01())),
//				CellFormatter.getStringCell(row.getCell(Setting.getPartnerAddCol02())),
//				CellFormatter.getStringCell(row.getCell(Setting.getPartnerAddCol03())),
//				CellFormatter.getStringCell(row.getCell(Setting.getPartnerAddCol04())));
		partnerAddCol01 = CellFormatter.getStringCell(row.getCell(Setting.getPartnerAddCol01()));
		if (partnerAddCol01 == null) {
			partnerAddCol01 = "";
		}
		partnerAddCol02 = CellFormatter.getStringCell(row.getCell(Setting.getPartnerAddCol02())) == null ? "" : CellFormatter.getStringCell(row.getCell(Setting.getPartnerAddCol02()));;
		if (partnerAddCol02 == null) {
			partnerAddCol02 = "";
		}
		partnerAddCol03 = CellFormatter.getStringCell(row.getCell(Setting.getPartnerAddCol03())) == null ? "" : CellFormatter.getStringCell(row.getCell(Setting.getPartnerAddCol03()));;
		if (partnerAddCol03 == null) {
			partnerAddCol03 = "";
		}
		partnerAddCol04 = CellFormatter.getStringCell(row.getCell(Setting.getPartnerAddCol04())) == null ? "" : CellFormatter.getStringCell(row.getCell(Setting.getPartnerAddCol04()));;
		if (partnerAddCol04 == null) {
			partnerAddCol04 = "";
		}
		productDetail = DataReader
				.getProductDetail(CellFormatter.getStringCell(row.getCell(Setting.getProductDetailCol())));
		productType = DataReader.getProductType(productDetail);
		volume = CellFormatter.getFloatCell(row.getCell(Setting.getVolumeCol()));
		unit = CellFormatter.getStringCell(row.getCell(Setting.getUnitCol()));
		unitPrice = CellFormatter.getFloatCell(row.getCell(Setting.getUnitPriceCol()));
		exchangeRate = CellFormatter.getFloatCell(row.getCell(Setting.getExchangeRateCol()));
		currencyCode = CellFormatter.getStringCell(row.getCell(Setting.getCurrencyCodeCol()));

		if (currencyCode != null && currencyCode.equals("VND")) {
			exchangeRate = 1f;
		}
		if (volume == null || unitPrice == null) {
			value = null;
		} else {
			value = volume * unitPrice;
		}
	}

	public void writeFile(String str) throws FileNotFoundException, IOException {
		File file = new File(Setting.getSqlFilePath());
		if (!file.exists()) {
			file.createNewFile();
		}
		try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
			writer.write(str);
		}
	}
}
