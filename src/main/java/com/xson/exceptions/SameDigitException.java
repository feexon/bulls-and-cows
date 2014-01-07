package com.xson.exceptions;

/**
 * @author Administrator
 * @version 1.0 14-1-7,下午7:03
 */
public class SameDigitException extends RuntimeException {
    final public int digit;
    public SameDigitException(int digit) {
        this.digit = digit;
    }
}
