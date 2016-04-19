package com.quoders.apps.android.treepolis.ui.wikiSelection;

import java.util.List;

import rx.Observable;

/**
 * Created by david on 17/04/16.
 */
public interface WikiTreeInteractor  {

    Observable<List<String>> loadWikiTreeLinks();

    void extractTreeInfo(String url);
}
