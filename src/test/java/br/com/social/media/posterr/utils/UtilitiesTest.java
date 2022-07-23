package br.com.social.media.posterr.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UtilitiesTest {

    @Test
    void fixDate_Ok_Test(){
        Assertions.assertEquals("2022-07-23T00:00", Utilities.fixDate("23/07/2022").toString());
    }

    @Test
    void fixDate_Empty_Test(){
        Assertions.assertNull(Utilities.fixDate(""));
    }

    @Test
    void validateUsername_Ok_Test(){
        Assertions.assertTrue(Utilities.validateUsername("gariela"));
    }

    @Test
    void validateUsername_False_Test(){
        Assertions.assertFalse(Utilities.validateUsername("g~~iela"));
    }
}
