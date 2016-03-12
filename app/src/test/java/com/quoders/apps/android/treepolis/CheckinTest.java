package com.quoders.apps.android.treepolis;

import android.content.Context;
import android.test.suitebuilder.annotation.MediumTest;

import com.quoders.apps.android.treepolis.domain.checkin.TakeTreePictureinInteractor;
import com.quoders.apps.android.treepolis.domain.checkin.TakeTreePictureinInteractorImpl;
import com.quoders.apps.android.treepolis.model.checkin.CheckinConsts;
import com.quoders.apps.android.treepolis.utils.IQSharedPrefs;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@MediumTest
public class CheckinTest {

    @Mock Context contextMock;
    @Mock IQSharedPrefs sharedPrefs;

    private TakeTreePictureinInteractor interactor;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        interactor = new TakeTreePictureinInteractorImpl(contextMock, sharedPrefs);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testBuildTreePicturePath_returns_wellFormedPath() {
        String path = interactor.buildTreePictureFileFullPath(R.id.circleButtonTreePhoto);

        Assert.assertTrue(path.contains(CheckinConsts.CHEKIN_TREE_PICTURE_FILE_NAME));
    }

    @Test
    public void testGetPendingUploadPictures_should_returnMapViewIdAndPath() {
        when(sharedPrefs.getString(CheckinConsts.CHEKIN_TREE_PICTURE_FILE_NAME)).thenReturn("fakePath/file.jpg");
        Map<Integer, String> pendingPictures = interactor.getPendingUploadPictures();

        Assert.assertTrue(pendingPictures != null);
        Assert.assertTrue(pendingPictures.size()==1);
        Assert.assertTrue(pendingPictures.get(R.id.circleButtonTreePhoto).equalsIgnoreCase("fakePath/file.jpg"));
    }
}
