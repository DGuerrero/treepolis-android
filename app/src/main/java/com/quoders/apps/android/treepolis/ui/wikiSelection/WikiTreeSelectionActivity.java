package com.quoders.apps.android.treepolis.ui.wikiSelection;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.quoders.apps.android.treepolis.BaseActivity;
import com.quoders.apps.android.treepolis.R;
import com.quoders.apps.android.treepolis.model.checkin.WikiTreeLink;
import com.quoders.apps.android.treepolis.ui.dialogs.QAlertDialog;
import com.quoders.apps.android.treepolis.ui.dialogs.QProgressDialog;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WikiTreeSelectionActivity extends BaseActivity implements WikiTreeSelectionView {

    @Bind(R.id.webViewWikiSelection) WebView mWebViewWiki;

    @OnClick(R.id.buttonWikiInfoSelect)
    public void onSelectTreeClick(View view) {
        mPresenter.onTreeInfoSelected(mWebViewWiki.getUrl());
    }

    private WikiTreeSelectionPresenter mPresenter;
    private QAlertDialog mDialog = new QAlertDialog(this);
    private QProgressDialog mProgressDialog = new QProgressDialog(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiki_tree_selection);
        ButterKnife.bind(this);
        mPresenter = new WikiTreeSelectionPresenterImpl();
        initToolbar();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onViewAttached(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onViewDetached();
    }

    @Override
    public void initWikiTreesWebview(final List<WikiTreeLink> treeLinks) {
        final String mainUrl = getString(R.string.wiki_trees_list_url);

        WebViewClient webViewClient = new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if(mainUrl.equalsIgnoreCase(url)) {
                    dismissLoadingProgressDialog();
                }
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                showLoadingProgressDialog();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                String urlTemp = url.substring(url.indexOf("/wiki"));
                if(treeLinks.contains(urlTemp)) {
                    return false;
                }
                return true;
            }
        };

        mWebViewWiki.setWebViewClient(webViewClient);
        mWebViewWiki.loadUrl(mainUrl);
    }

    @Override
    public void showErrorAccessingWikiTress() {
        mDialog.showDialogNeutral(R.string.dialog_title_error,
                R.string.wikitrees_info_loading_error,
                R.string.dialog_button_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
    }

    @Override
    public void dismissDialog() {
        if(mDialog != null) {
            mDialog.dismissDialog();
        }
    }

    @Override
    public void showLoadingProgressDialog() {
        mProgressDialog.show(R.string.wikitrees_info_loading_progress, false);
    }

    @Override
    public void dismissLoadingProgressDialog() {
        mProgressDialog.stop();
    }


}
