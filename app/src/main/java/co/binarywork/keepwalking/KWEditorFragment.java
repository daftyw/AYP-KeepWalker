package co.binarywork.keepwalking;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

import co.binarywork.keepwalking.model.WalkingItem;
import co.binarywork.keepwalking.model.WalkingItemLab;

/**
 * Created by wind on 7/27/2016 AD.
 */
public class KWEditorFragment extends Fragment implements View.OnClickListener {

    private static final String WALKING_ITEM = "KWEditor.WALKING_ITEM";

    public static KWEditorFragment newInstance(Integer walkingItemIndex) {
        Bundle args = new Bundle();
        if(walkingItemIndex > -1) {
            args.putInt(WALKING_ITEM, walkingItemIndex);
        }
        KWEditorFragment fragment = new KWEditorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    ////

    private WalkingItem mWalkingItem;
    private EditText mTitleEditText;
    private Button mButton;

    private boolean mIsNew;
    private WalkingItemLab _walkingItemLab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _walkingItemLab = WalkingItemLab.getInstance(getActivity());

        Bundle args = getArguments();
        Integer index = args.getInt(WALKING_ITEM, -1);
        if(index == -1) {
            mWalkingItem = WalkingItemLab.newWalkingItem("");
            mIsNew = true;
        } else {
            mWalkingItem = _walkingItemLab.getWalkingItem(index);
            mIsNew = false;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_editor, container, false);

        mTitleEditText = (EditText) v.findViewById(R.id.form_title_edit_text);
        mTitleEditText.setText(mWalkingItem.getTitle());

        mButton = (Button) v.findViewById(R.id.form_title_save_button);
        mButton.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View view) {
        mWalkingItem.setTitle(mTitleEditText.getText().toString());
        mWalkingItem.setDate(new Date());

        if(mIsNew) {
            _walkingItemLab.addWalkingItem(mWalkingItem);
        }

        Activity activity = getActivity();
        if(activity != null) {
            activity.finish();
        }
    }
}
