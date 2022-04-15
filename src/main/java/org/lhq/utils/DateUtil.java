package org.lhq.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class DateUtil {


	private DateUtil(){}
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final ThreadLocal<DateFormat> threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat(DATE_FORMAT));

	public static String getDateFormat(Date date) {
		DateFormat dateFormat = threadLocal.get();
		return dateFormat.format(date);
	}

	public static String getFormatDate(Date date, String format) {
		try {
			log.info("执行格式化");
			threadLocal.set(new SimpleDateFormat(format));
			DateFormat dateFormat = threadLocal.get();
			log.info("获得格式化后的数据");
			return dateFormat.format(date);
		} catch (Exception e) {
			return "";
		} finally {
			log.info("移除threadlocal");
			threadLocal.remove();
		}
	}

}

