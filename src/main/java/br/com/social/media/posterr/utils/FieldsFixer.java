package br.com.social.media.posterr.utils;

import br.com.social.media.posterr.application.enums.PostType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldsFixer {

    public static LocalDateTime fixDate(String date){
        if(!date.isEmpty()){
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            return LocalDate.parse(date, format).atStartOfDay();
        }

        return null;
    }

    public static boolean validateUsername(String userName){
        String regExp = "^[^<>{}\"/|;:.,~!?@#$%^=&*\\]\\\\()\\[¿§«»ω⊙¤°℃℉€¥£¢¡®©0-9_+]*$";
        Pattern regex = Pattern.compile(regExp);
        Matcher m = regex.matcher(userName);

        return m.matches();
    }

    public static String formatDate(LocalDateTime data){
        Locale local = new Locale("EN","US");
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MMMM dd, yyyy", local);
        return f.format(data);
    }

    public static boolean isTheActionValid(String action, String postType){
        if(action.equalsIgnoreCase(PostType.REPOST.name())){
            return postType.equalsIgnoreCase(PostType.QUOTE.name()) || postType.equalsIgnoreCase(PostType.PERSONAL.name());
        }
        else if(action.equalsIgnoreCase(PostType.QUOTE.name())){
            return postType.equalsIgnoreCase(PostType.REPOST.name()) || postType.equalsIgnoreCase(PostType.PERSONAL.name());
        }
        return false;
    }

}
