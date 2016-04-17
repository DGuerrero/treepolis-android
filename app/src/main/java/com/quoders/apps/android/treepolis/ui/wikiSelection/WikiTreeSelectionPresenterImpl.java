package com.quoders.apps.android.treepolis.ui.wikiSelection;

import com.quoders.apps.android.treepolis.BaseView;
import com.quoders.apps.android.treepolis.model.checkin.WikiTreeLink;

import java.util.ArrayList;
import java.util.List;

public class WikiTreeSelectionPresenterImpl implements WikiTreeSelectionPresenter {

    private List<WikiTreeLink> mTreeLinks = new ArrayList();


    @Override
    public void onViewAttached(BaseView view) {
        WikiTreeInteractor wikiTreeInteractor = new WikiTreeInteractorImpl((WikiTreeSelectionActivity)view);
        mTreeLinks = wikiTreeInteractor.loadWikiTreeLinks();
    }

    @Override
    public void onViewDetached() {

    }
}
