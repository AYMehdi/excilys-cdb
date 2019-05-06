package com.excilys.model.mapper;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimestampMapper {
	
// ******* VARIABLE *******	
	private static Logger logger = LoggerFactory.getLogger(CompanyMapper.class);
	
// ******* METHODS *******	
	/**
	 * Return an Optional<Timestamp> mapped with a String associated
	 * @param timestanmpStr String timestanmp
	 * @return Optional<Timestamp>
	 */
	public static Optional<Timestamp> stringToTimestamp(String timestampStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
		
		try {
			LocalDate parsedDate = LocalDate.parse(timestampStr, formatter);
			Timestamp time = Timestamp.valueOf(parsedDate.atStartOfDay());
			return Optional.of(time);
		} catch (DateTimeParseException e) {
			e.getStackTrace();
			logger.error("DateTimeParseException", e);
			throw new DateTimeParseException("DateTimeParseException in com.excilys.model.mapper.TimesstampMapper.java, method stringToTimestamp(String timestampStr)", timestampStr, 0);
		}
	}

	/**
	 * Return an String mapped with a Timestamp associated
	 * @param timestamp Timestamp timestanmp
	 * @return String
	 */
	public static String timestampToString(Timestamp timestamp) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Date date = new Date(timestamp.getTime());
		return formatter.format(formatter.parse(date.toString()));
	}

	/**
	 * Return current time in a Timestamp
	 * @return Timestamp current time
	 */
	public static Timestamp currentTimeToTimestamp() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formatDateTime = now.format(formatter);
		return Timestamp.valueOf(formatDateTime);
	}
}