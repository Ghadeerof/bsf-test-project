package com.bsf.utils;

import com.bsf.builder.AccountBuilder;
import org.apache.commons.lang3.ObjectUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AssertionUtils {

    public static void assertAccount(AccountBuilder account, AccountBuilder response)
    {
        assertTrue(ObjectUtils.isNotEmpty(account) && ObjectUtils.isNotEmpty(response));
        assertEquals(account.getAccountId(), response.getAccountId());
        assertEquals(account.getFullName(), response.getFullName());
        assertEquals(account.getAccountBalance(), response.getAccountBalance());
        assertEquals(account.getEmailAddress(), response.getEmailAddress());
    }
}
