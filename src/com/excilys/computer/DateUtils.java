package com.excilys.computer;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;

public class DateUtils {
	 
	public static Date toSQLDate(LocalDate date) {
		return Date.valueOf(date);
	}
 
	public static Date toSQLDate(java.util.Date date) {
		if (date instanceof Date ) {
			return (Date) date;
		}
		return new java.sql.Date(toDate(date).getTime());
	}
 
	public static Date toSQLDate(Date date) {
		return date;
	}
 
	///
 
	public static java.util.Date toDate(LocalDate date) {
		Instant instant = date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		return Date.from(instant);
	}
 
	private static final int[] HMS_FIELDS = {Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND, Calendar.MILLISECOND};
	public static java.util.Date toDate(Date date) {
		if ( date instanceof java.util.Date ) {
			return toDate((java.util.Date)date);
		}
		return date;
	}
 
	public static java.util.Date toDate(java.util.Date date) {
		final boolean sql;
		if ( date instanceof Date ) {
			sql=true;
		}
		else {
			sql=false;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		for(int field : HMS_FIELDS) {
			cal.set(field, 0);
		}
		return sql?new java.sql.Date(cal.getTimeInMillis()):cal.getTime(); // supprime h,m,s
	}
 
	///
 
	public static LocalDate toLocalDate(java.util.Date date) {
		Instant instant = Instant.ofEpochMilli(date.getTime());
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
	}
 
	public static LocalDate toLocalDate(java.sql.Date date) {
		return toLocalDate(toDate(date));
	}
 
	public static LocalDate toLocalDate(LocalDate date) {
		return date;
	}
 
	///
 
	public static int compare(Date date1, Date date2) {
		return date1.compareTo(date2);
	}
 
	public static int compare(Date date1, LocalDate date2) {
		return date1.compareTo(toSQLDate(date2));
	}
 
	public static int compare(Date date1, java.util.Date date2) {
		return date1.compareTo(toSQLDate(date2));
	}
 
	///
 
	public static int compare(LocalDate date1, Date date2) {
		return -compare(date2, date1);
	}
 
	public static int compare(LocalDate date1, LocalDate date2) {
		return date1.compareTo(date2);
	}
 
	public static int compare(LocalDate date1, java.util.Date date2) {
		return -compare(date2, date1);
	}
 
	///
 
	public static int compare(java.util.Date date1, Date date2) {
		return -compare(date2, date1);
	}
 
	public static int compare(java.util.Date date1, LocalDate date2) {
		return compare(date1, toSQLDate(date2));
	}
 
	public static int compare(java.util.Date date1, java.util.Date date2) {
		return toSQLDate(date1).compareTo(toSQLDate(date2));
	}
 
	///
 
	public static void main(String[] args) {
		System.out.println("SQLDate: " + toSQLDate(LocalDate.now()));
		System.out.println("Date: " + toDate(LocalDate.now()));
		System.out.println("SQLDate: " + toSQLDate(new java.util.Date()));
		System.out.println("LocalDate: " + toLocalDate(new java.util.Date()));
 
		Date sqlNow = Date.valueOf(LocalDate.now()); // Java 8
		//Date sqlNow = toSQLDate(new java.util.Date()); // Java 7
		System.out.println("SQL now: " + sqlNow);
		java.util.Date dateNow = toDate(new java.util.Date());
		System.out.println("Date now: " + dateNow);
		LocalDate now = LocalDate.now();
		System.out.println("LocalDate now: " + now);
		System.out.println("SQL = date: " + (compare(sqlNow,dateNow)==0));
		System.out.println("SQL = LocalDate : " + (compare(sqlNow,now)==0));
		System.out.println("LocalDate = date: " + (compare(now,dateNow)==0));
	}
 
}