package com.quoders.apps.android.treepolis.ui.wikiSelection;

import com.quoders.apps.android.treepolis.BaseView;
import com.quoders.apps.android.treepolis.model.checkin.WikiTreeLink;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class WikiTreeSelectionPresenterImpl implements WikiTreeSelectionPresenter {

    private WikiTreeSelectionView mView;
    private WikiTreeInteractor mInteractor;
    private CompositeSubscription mSubscriptions = new CompositeSubscription();


    @Override
    public void onViewAttached(BaseView view) {
        this.mView = (WikiTreeSelectionActivity)view;
        this.mInteractor = new WikiTreeInteractorImpl((WikiTreeSelectionActivity)mView);
        mView.showLoadingProgressDialog();
        getWikiTreeLinks();
    }

    @Override
    public void onViewDetached() {
        mSubscriptions.clear();
        mView.dismissDialog();
    }

    private void getWikiTreeLinks() {

        mSubscriptions.add(mInteractor.loadWikiTreeLinks()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                displayErrorLoadingTreesInfo();
            }

            @Override
            public void onNext(List<String> treeLinks) {
                if(treeLinks != null) {
                    mView.initWikiTreesWebview(treeLinks);
                } else {
                    displayErrorLoadingTreesInfo();
                }
            }
        }));
    }

    private void displayErrorLoadingTreesInfo() {
        mView.dismissLoadingProgressDialog();
        mView.showErrorAccessingWikiTress();
    }

    @Override
    public void onTreeInfoSelected(String url) {
        mInteractor.extractTreeInfo(url).subscribe(new Action1<WikiTreeLink>() {
            @Override
            public void call(WikiTreeLink wikiTreeLink) {
                mView.returnSelectedWikiTree(wikiTreeLink);
            }
        });
    }
}
