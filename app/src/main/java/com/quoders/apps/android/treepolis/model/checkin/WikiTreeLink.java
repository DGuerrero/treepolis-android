package com.quoders.apps.android.treepolis.model.checkin;

/**
 * Created by davidguerrerodiaz on 13/04/16.
 */
public class WikiTreeLink {

    private String mName;
    private String mWikiLink;

    public WikiTreeLink(String name, String wikiLink) {
        this.mName = mName;
        this.mWikiLink = mWikiLink;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getWikiLink() {
        return mWikiLink;
    }

    public void setWikiLink(String mWikiLink) {
        this.mWikiLink = mWikiLink;
    }
}
