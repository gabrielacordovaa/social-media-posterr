package br.com.social.media.posterr.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilities {

    public static LocalDateTime fixDate(String date){
        if(! date.isEmpty()){
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime fixDate = LocalDate.parse(date, format).atStartOfDay();

            return fixDate;
        }

        return null;
    }

    //todo: check this
    public static boolean validateUsername(String userName){
        String regExp = "^[^<>{}\"/|;:.,~!?@#$%^=&*\\]\\\\()\\[¿§«»ω⊙¤°℃℉€¥£¢¡®©0-9_+]*$";
        Pattern regex = Pattern.compile(regExp);
        Matcher m = regex.matcher(userName);

        return m.matches();
    }
}
