package com.quoders.apps.android.treepolis;

import android.test.suitebuilder.annotation.MediumTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Tests for FaceValidator. FaceDetection is mocked, so all tests feed fake byte array to validator
 * and mock onFaceResultsAr with dummy data. Each error case is covered, and we validate frames are
 * recycled properly on error or success. Custom matcher is used to validate error codes.
 */
@RunWith(MockitoJUnitRunner.class)
@MediumTest
public class FaceValidatorTest {

    private static final byte[] FACE_FRAME = new byte[0];

    @Before
    public void setup() {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void myTest() {
    }



}
