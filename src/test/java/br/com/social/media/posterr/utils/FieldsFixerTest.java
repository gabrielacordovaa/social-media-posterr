package br.com.social.media.posterr.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class FieldsFixerTest {

    @Test
    void fixDate_Ok_Test(){
        Assertions.assertEquals("2022-07-23T00:00", FieldsFixer.fixDate("23/07/2022").toString());
    }

    @Test
    void fixDate_Empty_Test(){
        Assertions.assertNull(FieldsFixer.fixDate(""));
    }

    @Test
    void validateUsername_Ok_Test(){
        Assertions.assertTrue(FieldsFixer.validateUsername("gariela"));
    }

    @Test
    void validateUsername_False_Test(){
        Assertions.assertFalse(FieldsFixer.validateUsername("g~~iela"));
    }

    @Test
    void formatDateTest(){
        Assertions.assertEquals("July 24, 2022", FieldsFixer.formatDate(LocalDateTime.parse("2022-07-24T11:48:12.34")));
    }

    @Test
    void isTheActionValidRepostQuoteTrueTest(){
        Assertions.assertTrue(FieldsFixer.isTheActionValid("REPOST", "QUOTE"));
    }

    @Test
    void isTheActionValidRepostPersonalTrueTest(){
        Assertions.assertTrue(FieldsFixer.isTheActionValid("REPOST", "personal"));
    }

    @Test
    void isTheActionValidRepostFalseTest(){
        Assertions.assertFalse(FieldsFixer.isTheActionValid("REPOST", "repost"));
    }

    @Test
    void isTheActionValidQuoteRespostTrueTest(){
        Assertions.assertTrue(FieldsFixer.isTheActionValid("QUOTE", "repost"));
    }
    @Test
    void isTheActionValidQuotePersonalTrueTest(){
        Assertions.assertTrue(FieldsFixer.isTheActionValid("QUOTE", "personal"));
    }
    @Test
    void isTheActionValidQuoteFalseTest(){
        Assertions.assertFalse(FieldsFixer.isTheActionValid("QUOTE", "quote"));
    }

    @Test
    void isTheActionValidFalseTest(){
        Assertions.assertFalse(FieldsFixer.isTheActionValid("personal", "repost"));
    }
}
