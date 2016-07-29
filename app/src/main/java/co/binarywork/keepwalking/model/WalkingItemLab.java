package co.binarywork.keepwalking.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rawin on 29-Jul-16.
 */
public class WalkingItemLab {
    private static WalkingItemLab _instance;

    public static WalkingItemLab getInstance(Context context) {
        if(_instance == null) {
            _instance = new WalkingItemLab();
        }

        return _instance
                ;
    }

    public static WalkingItem newWalkingItem(String title) {
        return new WalkingItem(title);
    }


    private WalkingItemLab() {
        _walkingItemList = new ArrayList<>();
    }

    private List<WalkingItem> _walkingItemList;

    public List<WalkingItem> getWalkingItemList() {
        return _walkingItemList;
    }

    public WalkingItem getWalkingItem(Integer index) {
        if(_walkingItemList != null) {
            return _walkingItemList.get(index);
        }
        return null;
    }

    public void addWalkingItem(WalkingItem walkingItem) {
        assert walkingItem != null;
        _walkingItemList.add(walkingItem);
    }
}
