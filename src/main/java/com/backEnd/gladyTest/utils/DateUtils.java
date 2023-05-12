package com.backEnd.gladyTest.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static String dateToStringParse(Date date) {
		SimpleDateFormat formater = new SimpleDateFormat("MM/dd/yyyy");
		return formater.format(date);
	}

	public static Date parseDate(String date) {

		try {
			return new SimpleDateFormat("MM/dd/yyyy").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;

	}
}
