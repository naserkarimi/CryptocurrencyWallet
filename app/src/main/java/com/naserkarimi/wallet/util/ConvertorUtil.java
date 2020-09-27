package com.naserkarimi.wallet.util;

import java.math.BigDecimal;

public class ConvertorUtil {
    public static String getBitcoinFromSatoshi(Long satoshi) {
        return new BigDecimal(String.valueOf(satoshi)).movePointLeft(8).toString();
    }
}
