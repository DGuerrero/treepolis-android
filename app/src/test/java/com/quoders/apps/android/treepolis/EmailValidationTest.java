package com.quoders.apps.android.treepolis;

import android.test.suitebuilder.annotation.SmallTest;

import com.quoders.apps.android.treepolis.ui.signup.SignupPresenterImpl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by davidguerrerodiaz on 02/04/16.
 */
@RunWith(MockitoJUnitRunner.class)
@SmallTest
public class EmailValidationTest {

    String EMAIL_REGEX = SignupPresenterImpl.REGEX_EMAIL;

    @Test
    public void givenValidEmail_RegexCheckReturnsValid() throws Exception {
        String validEmail = "david.guerrero@quoders.com";
        Assert.assertTrue(validEmail.matches(EMAIL_REGEX));
    }

    @Test
    public void givenInvalidEmail_MissingAt_RegexCheckReturnsError() throws Exception {
        String invalidEmail = "david.guerreroquoders.com";
        Assert.assertFalse(invalidEmail.matches(EMAIL_REGEX));
    }

    @Test
    public void givenInvalidEmail_MissingHost_RegexCheckReturnsError() throws Exception {
        String invalidEmail = "david.guerrero@quoders";
        Assert.assertFalse(invalidEmail.matches(EMAIL_REGEX));
    }

    @Test
    public void givenInvalidEmail_Empty_RegexCheckReturnsError() throws Exception {
        String invalidEmail = "";
        Assert.assertFalse(invalidEmail.matches(EMAIL_REGEX));
    }

    @Test
    public void givenInvalidEmail_InvalidSymbols_RegexCheckReturnsError() throws Exception {
        String invalidEmail = "david.guerrero%quoders.com";
        Assert.assertFalse(invalidEmail.matches(EMAIL_REGEX));
    }
}
