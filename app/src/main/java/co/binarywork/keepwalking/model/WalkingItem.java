package co.binarywork.keepwalking.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wind on 7/26/2016 AD.
 */
public class WalkingItem implements Serializable {
    private String mTitle;
    private Date mDate;

    public WalkingItem() { mDate = new Date(); }
    public WalkingItem(String title) {
        this();
        this.mTitle = title; }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }
}
