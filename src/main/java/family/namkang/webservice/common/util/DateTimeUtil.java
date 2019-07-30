package family.namkang.webservice.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import lombok.Getter;

public class DateTimeUtil {
	
	public static String getCurrentyyyy() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy"));
	}
	public static String getCurrentMM() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM"));
	}
	public static String getCurrentdd() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd"));
	}
	
    
    public static String toString(LocalDateTime localDateTime, Pattern pattern){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern.getValue());
        return Optional.ofNullable(localDateTime)
                .map(formatter::format)
                .orElse("");
    }
    
    public static LocalDateTime fromString(String str, Pattern pattern){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern.getValue());
        try {
            return LocalDateTime.parse(str, formatter);
        } catch (DateTimeParseException e) {
        }
        return null;
        
    }
    

    public enum Pattern {

        YYYYMMDDHMS("yyyy.MM.dd HH:mm:ss"),
        YYYYMMDD("yyyy.MM.dd");

        @Getter
        private String value;

        Pattern(String value) {
            this.value = value;
        }
    }

}
