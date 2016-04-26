package com.quoders.apps.android.treepolis.ui.wikiSelection;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import com.quoders.apps.android.treepolis.BaseActivity;
import com.quoders.apps.android.treepolis.R;
import com.quoders.apps.android.treepolis.model.checkin.WikiTreeLink;
import com.quoders.apps.android.treepolis.ui.dialogs.QAlertDialog;
import com.quoders.apps.android.treepolis.ui.dialogs.QProgressDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WikiTreeSelectionActivity extends BaseActivity implements WikiTreeSelectionView {

    public static final String WIKI_TREE_DATA_RESULT = "com.quoders.apps.android.treepolis.ui.wikiSelection.WIKI_TREE_DATA_RESULT";

    private String mMainUrl;
    private WikiTreeSelectionPresenter mPresenter;
    private QAlertDialog mDialog = new QAlertDialog(this);
    private QProgressDialog mProgressDialog = new QProgressDialog(this);

    @BindView(R.id.webViewWikiSelection) WebView mWebViewWiki;
    @BindView(R.id.buttonWikiInfoSelect) Button mButtonSelectTree;
    @BindView(R.id.progressBarWebviewLoading) ContentLoadingProgressBar mProgressBar;

    @OnClick(R.id.buttonWikiInfoSelect)
    public void onSelectTreeClick(View view) {
        mPresenter.onTreeInfoSelected(mWebViewWiki.getUrl());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiki_tree_selection);
        ButterKnife.bind(this);
        initToolbar();
        mPresenter = new WikiTreeSelectionPresenterImpl();
        mMainUrl = getString(R.string.wiki_trees_list_url);
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
    public void initWikiTreesWebview(final List<String> treeLinks) {

        WebViewClient webViewClient = new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProgressBar.setVisibility(View.GONE);
                if(mMainUrl.equalsIgnoreCase(url)) {
                    dismissLoadingProgressDialog();
                    mButtonSelectTree.setEnabled(false);
                } else {
                    mButtonSelectTree.setEnabled(true);
                }
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mButtonSelectTree.setEnabled(false);
                mProgressBar.setVisibility(View.VISIBLE);
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
        mWebViewWiki.loadUrl(mMainUrl);
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

    @Override
    public void returnSelectedWikiTree(WikiTreeLink wikiTreeLink) {
        Intent intent = new Intent();
        intent.putExtra(WIKI_TREE_DATA_RESULT, wikiTreeLink);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if(!mMainUrl.equalsIgnoreCase(mWebViewWiki.getUrl())) {
            mWebViewWiki.goBack();
            return;
        }
        super.onBackPressed();
    }
}
