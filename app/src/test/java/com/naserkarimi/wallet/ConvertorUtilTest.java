package com.naserkarimi.wallet;

import com.naserkarimi.wallet.util.ConvertorUtil;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConvertorUtilTest {
    @Test
    public void getBitcoinFromSatoshi() {
        assertEquals("0.00006102", ConvertorUtil.getBitcoinFromSatoshi(6102L));
    }
}
