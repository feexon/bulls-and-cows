package com.xson;

import com.xson.exceptions.DifferentSizeException;
import com.xson.exceptions.SameDigitException;
import org.junit.Test;

import static com.xson.Digits.parse;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

/**
 * @author Administrator
 * @version 1.0 14-1-7,下午5:48
 */
public class DigitsTest {
    private Digits _1234 = parse("1234");

    @Test
    public void allAreBulls() throws Exception {
        assertThat(_1234.bulls(_1234), equalTo(4));
        assertThat(_1234.cows(_1234), equalTo(0));
    }

    @Test
    public void someBullsAreTogether() throws Exception {
        assertThat(_1234.bulls(parse("1256")), equalTo(2));
    }

    @Test
    public void bullsNotTogether() throws Exception {
        assertThat(_1234.bulls(parse("1564")), equalTo(2));
    }

    @Test
    public void noBulls() throws Exception {
        assertThat(_1234.bulls(parse("4321")), equalTo(0));
    }

    @Test
    public void allAreCows() throws Exception {
        assertThat(_1234.cows(parse("4321")), equalTo(4));
        assertThat(_1234.bulls(parse("4321")), equalTo(0));
    }

    @Test
    public void containsSomeCowsTogether() throws Exception {
        assertThat(_1234.cows(parse("4390")), equalTo(2));
    }

    @Test
    public void cowsNotTogether() throws Exception {
        assertThat(_1234.cows(parse("4913")), equalTo(3));
    }

    @Test
    public void cowsAndBulls() throws Exception {
        assertThat(_1234.cows(parse("1324")), equalTo(2));
        assertThat(_1234.bulls(parse("1324")), equalTo(2));
    }

    @Test
    public void noBullsAndCows() throws Exception {
        assertThat(_1234.bulls(parse("5678")), equalTo(0));
        assertThat(_1234.cows(parse("5678")), equalTo(0));
    }

    @Test
    public void should_containingSameDigits_raiseException() throws Exception {
        try {
            parse("1123");
            fail("containing same digit");
        } catch (SameDigitException expected) {
            assertThat(expected.digit, equalTo(1));
        }
    }

    @Test
    public void should_badForm_raiseException() throws Exception {
        try {
            parse("abc");
            fail("bad form");
        } catch (NumberFormatException expected) {
            assertThat(expected.getMessage(), equalTo("abc"));
        }
    }

    @Test
    public void should_lengthMoreThan10_raiseException() throws Exception {
        try {
            parse("01234567891");
            fail("should out of range raise exception");
        } catch (IllegalArgumentException expected) {
            assertThat(expected.getMessage(), equalTo("Digits out of range: 11"));
        }
    }

    @Test
    public void should_lengthEqualTo0_raiseException() throws Exception {
        try {
            parse("");
            fail("should parse empty form raise exception!");
        } catch (IllegalArgumentException expected) {
            assertThat(expected.getMessage(), equalTo("Digits can't be empty!"));
        }
    }

    @Test
    public void should_cows_withDifferentSizeDigits_raiseException() throws Exception {
        try {
            _1234.cows(Digits.parse("12345"));
            fail("should operate digits on different size raise exception!");
        } catch (DifferentSizeException expected) {
        }
    }

    @Test
    public void should_bulls_withDifferentSizeDigits_raiseException() throws Exception {
        try {
            _1234.bulls(Digits.parse("12345"));
            fail("should operate digits on different size raise exception!");
        } catch (DifferentSizeException expected) {
        }
    }
}
