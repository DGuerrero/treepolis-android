package com.quoders.apps.android.treepolis.ui.wikiSelection;

import com.quoders.apps.android.treepolis.model.checkin.WikiTreeLink;

import java.util.List;

import rx.Observable;

/**
 * Created by david on 17/04/16.
 */
public interface WikiTreeInteractor  {

    Observable<List<String>> loadWikiTreeLinks();

    Observable<WikiTreeLink> extractTreeInfo(String url);
}
