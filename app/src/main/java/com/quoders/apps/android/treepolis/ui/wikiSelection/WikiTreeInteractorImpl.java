package com.quoders.apps.android.treepolis.ui.wikiSelection;

import android.content.Context;

import com.quoders.apps.android.treepolis.R;
import com.quoders.apps.android.treepolis.model.checkin.WikiTreeLink;
import com.quoders.apps.android.treepolis.utils.FileUtils;

import java.util.List;

import rx.Observable;

/**
 * Created by david on 17/04/16.
 */
public class WikiTreeInteractorImpl implements WikiTreeInteractor {
    private final Context mContext;

    public WikiTreeInteractorImpl(Context context) {
        this.mContext = context;
    }

    @Override
    public Observable<List<WikiTreeLink>> loadWikiTreeLinks() {

        Observable.from()
        String wikiTrees = FileUtils.readJsonFile(mContext, mContext.getString(R.string.wiki_trees_json));
        if(wikiTrees != null && !wikiTrees.isEmpty()) {

        }
        return null;
    }
}
