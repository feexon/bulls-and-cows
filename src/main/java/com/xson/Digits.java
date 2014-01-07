package com.xson;

import com.xson.exceptions.DifferentSizeException;
import com.xson.exceptions.SameDigitException;

/**
 * @author Administrator
 * @version 1.0 14-1-7,下午5:49
 */
public class Digits {
    private final int[] digits;
    private final int mask;

    public static Digits parse(String form) {
        checkConstrains(form);
        char[] source = form.toCharArray();
        int[] digits = new int[source.length];
        for (int i = 0; i < source.length; i++) {
            int candidate = Character.digit(source[i], 10);
            if (candidate == -1) {
                throw new NumberFormatException(form);
            }
            digits[i] = candidate;
        }
        return new Digits(digits);
    }

    private static void checkConstrains(String form) {
        if (form.isEmpty()) {
            throw new IllegalArgumentException("Digits can't be empty!");
        }
        if (form.length() > 10) {
            throw new IllegalArgumentException("Digits out of range: " + form.length());
        }
    }

    private Digits(int[] digits) {
        this.digits = digits;
        this.mask = compile(digits);
    }

    static private int compile(int[] digits) {
        int mask = 0;
        for (int digit : digits) {
            if (contains(mask, digit)) {
                throw new SameDigitException(digit);
            }
            mask |= mask4(digit);
        }
        return mask;
    }

    static private int mask4(int digit) {
        return 1 << digit;
    }

    public int bulls(Digits that) {
        checkingSize(that);
        int bulls = 0;
        for (int i = 0; i < numberOfDigits(); i++) {
            if (sameAt(that, i)) {
                bulls++;
            }
        }
        return bulls;
    }

    private void checkingSize(Digits that) {
        if (that.numberOfDigits() != numberOfDigits()) {
            throw new DifferentSizeException();
        }
    }

    private boolean sameAt(Digits that, int pos) {
        return digit(pos) == that.digit(pos);
    }

    private int digit(int pos) {
        return digits[pos];
    }

    public int cows(Digits that) {
        checkingSize(that);
        int cows = 0;
        for (int pos = 0; pos < numberOfDigits(); pos++) {
            if (!sameAt(that, pos) && contains(that.digit(pos))) {
                cows++;
            }
        }
        return cows;
    }

    private boolean contains(int digit) {
        return contains(mask, digit);
    }

    private static boolean contains(int mask, int digit) {
        return (mask & mask4(digit)) != 0;
    }

    public int numberOfDigits() {
        return digits.length;
    }
}
