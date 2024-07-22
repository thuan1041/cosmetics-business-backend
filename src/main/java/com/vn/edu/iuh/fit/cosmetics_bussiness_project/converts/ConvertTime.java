package com.vn.edu.iuh.fit.cosmetics_bussiness_project.converts;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ConvertTime {

	public static Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
		// Default system zone
		ZoneId defaultZoneId = ZoneId.systemDefault();

		// Convert LocalDateTime to Instant
		Instant instant = localDateTime.atZone(defaultZoneId).toInstant();

		// Convert Instant to Date
		return Date.from(instant);
	}
	
    public static LocalDateTime convertDateToLocalDateTime(Date date) {
        if (date == null) {
            throw new IllegalArgumentException("The date parameter cannot be null");
        }
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
}
