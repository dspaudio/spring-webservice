package family.namkang.webservice.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import lombok.Getter;

public class DateTimeUtil {
	
	public static String toStringWithPattern(LocalDateTime localDateTime, DateTimePattern pattern){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern.getValue());
        return Optional.ofNullable(localDateTime)
                .map(formatter::format)
                .orElse("");
    }
	
	public static LocalDateTime toDateTimeWithPattern(String str, DateTimePattern pattern){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern.getValue());
        try {
        	return LocalDateTime.parse(str, formatter);
        } catch (DateTimeParseException e) {
        }
    	return null;
        
    }
	

    public enum DateTimePattern {

        YYYYMMDDHMS("yyyy-MM-dd HH:mm:ss"),
        YYYYMMDD("yyyy-MM-dd");

        @Getter
        private String value;

        DateTimePattern(String value) {
            this.value = value;
        }
    }

}
