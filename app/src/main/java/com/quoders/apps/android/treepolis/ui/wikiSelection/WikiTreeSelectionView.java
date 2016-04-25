package com.quoders.apps.android.treepolis.ui.wikiSelection;

import com.quoders.apps.android.treepolis.BaseView;
import com.quoders.apps.android.treepolis.model.checkin.WikiTreeLink;

import java.util.List;

/**
 * Created by davidguerrerodiaz on 21/03/16.
 */
public interface WikiTreeSelectionView extends BaseView {

    void initWikiTreesWebview(final List<String> treeLinks);

    void showErrorAccessingWikiTress();

    void dismissDialog();

    void showLoadingProgressDialog();

    void dismissLoadingProgressDialog();

    void returnSelectedWikiTree(WikiTreeLink wikiTreeLink);
}
