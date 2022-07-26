package br.com.social.media.posterr.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;

 class FieldsFixerTest {

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

    @DisplayName("Repost - true")
    @ParameterizedTest
    @ValueSource(strings = {"quote", "personal"})
    void isTheActionValidRepostQuoteTrueTest(String type){
        Assertions.assertTrue(FieldsFixer.isTheActionValid("REPOST", type));
    }

    @Test
    void isTheActionValidRepostFalseTest(){
        Assertions.assertFalse(FieldsFixer.isTheActionValid("REPOST", "repost"));
    }

    @DisplayName("Quote - true")
    @ParameterizedTest
    @ValueSource(strings = {"repost", "personal"})
    void isTheActionValidQuoteRespostTrueTest(String type){
        Assertions.assertTrue(FieldsFixer.isTheActionValid("QUOTE", type));
    }
    @Test
    void isTheActionValidQuoteFalseTest(){
        Assertions.assertFalse(FieldsFixer.isTheActionValid("QUOTE", "quote"));
    }

    @DisplayName("PERSONAL - false")
    @ParameterizedTest
    @ValueSource(strings = {"quote", "personal", "repost"})
    void isTheActionValidFalseTest(String type){
        Assertions.assertFalse(FieldsFixer.isTheActionValid("personal", type));
    }
}
