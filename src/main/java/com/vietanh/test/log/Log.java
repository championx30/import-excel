
package com.vietanh.test.log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import com.vietanh.test.setting.Setting;

public class Log {

	public boolean write(long start, long finish, int countRowRead, int countBlankTrader, int countTaxCodeIsNull,
			int countRecordDb, int countRecordFile) throws IOException {
		File file = new File(Setting.getLogFilePath());
		if (!file.exists()) {
			file.createNewFile();
		}
		try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file, true),
				StandardCharsets.UTF_8)) {
			Date date = new Date();
			writer.write("#" + date.toString());
			writer.write("\nThời gian chạy tool: " + (float) (finish - start) / 1000 + "(s)");
			writer.write("\nTổng số 'Row' đã đọc trong Excel: " + countRowRead);
			writer.write("\nTổng số 'Row' bị loại do 'Nhà giao dịch' trống: " + countBlankTrader);
			writer.write("\nTổng số 'Row' bị loại 'Mã số thuế = \"9999999999998\"': " + countTaxCodeIsNull);
			writer.write("\nTổng số 'bản ghi' đã insert vào Database: " + countRecordDb);
			writer.write("\nTổng số 'bản ghi' đã ghi vào File: " + countRecordFile);
			writer.write("\nSố 'Row' bị lỗi không xác định: "
					+ (countRowRead - countBlankTrader - countTaxCodeIsNull - countRecordDb - countRecordFile));
			writer.write("\n=======================================================\n");
			return countRowRead - countBlankTrader - countTaxCodeIsNull - countRecordDb - countRecordFile == 0;
		}
	}
}
