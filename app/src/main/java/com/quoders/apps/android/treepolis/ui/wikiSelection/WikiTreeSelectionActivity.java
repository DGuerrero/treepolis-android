package com.quoders.apps.android.treepolis.ui.wikiSelection;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.quoders.apps.android.treepolis.BaseActivity;
import com.quoders.apps.android.treepolis.R;
import com.quoders.apps.android.treepolis.model.checkin.WikiTreeLink;
import com.quoders.apps.android.treepolis.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WikiTreeSelectionActivity extends BaseActivity implements WikiTreeSelectionView {

    @Bind(R.id.webViewWikiSelection) WebView mWebViewWiki;
    private List<WikiTreeLink> mTreeLinks = new ArrayList();

    @OnClick(R.id.buttonWikiInfoSelect)
    public void onSelectTreeClick(View view) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiki_tree_selection);
        ButterKnife.bind(this);
        initToolbar();
        initWebview();
        loadTreesLinks();
    }

    private void loadTreesLinks() {
        String wikiTrees = FileUtils.readJsonFile(this, getString(R.string.wiki_trees_json));
        if(wikiTrees != null) {

        }
    }

    private void initWebview() {
        WebViewClient webViewClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                String urlTemp = url.substring(url.indexOf("/wiki"));
                if(mTreeLinks.contains(urlTemp)) {
                    return false;
                }
                return true;
            }
        };

        mWebViewWiki.setWebViewClient(webViewClient);
        mWebViewWiki.loadUrl(getString(R.string.wiki_trees_list_url));
    }
}
