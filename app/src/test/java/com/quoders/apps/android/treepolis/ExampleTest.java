package com.quoders.apps.android.treepolis;

import android.test.suitebuilder.annotation.SmallTest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
@SmallTest
public class ExampleTest {

    EmailValidator mEmailValidator;

    @Before
    public void setUp() throws Exception {
        mEmailValidator = new EmailValidator();
    }

    @Test
    public void givenValidEmail_ReturnsValid() throws Exception {
        String validEmail = "david.guerrero@quoders.com";
        Assert.assertTrue(mEmailValidator.isValidEmail(validEmail));
    }

    @Test
    public void givenInvalidEmail_MissingHost_ReturnsError() throws Exception {
        String invalidEmail = "david.guerrero.com";
        Assert.assertFalse(mEmailValidator.isValidEmail(invalidEmail));
    }

    @After
    public void tearDown() throws Exception {
        mEmailValidator.releaseResources();
    }
}
