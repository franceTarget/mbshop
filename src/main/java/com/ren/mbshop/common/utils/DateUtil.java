package com.ren.mbshop.common.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static Date now(){
        return new Timestamp(System.currentTimeMillis());
    }
	
	public static String DateToString(String s,Date date) {
		SimpleDateFormat format = new SimpleDateFormat(s);
		return format.format(date);
	}
}
