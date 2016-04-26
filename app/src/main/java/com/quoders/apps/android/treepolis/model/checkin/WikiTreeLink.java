package com.quoders.apps.android.treepolis.model.checkin;

import java.io.Serializable;

/**
 * Created by davidguerrerodiaz on 13/04/16.
 */
public class WikiTreeLink implements Serializable {

    private String name;
    private String link;

    public WikiTreeLink(String name, String wikiLink) {
        this.name = name;
        this.link = wikiLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String mName) {
        this.name = mName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String mWikiLink) {
        this.link = mWikiLink;
    }
}
