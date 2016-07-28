package co.binarywork.keepwalking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by wind on 7/27/2016 AD.
 */
public class KWEditorFragment extends Fragment {

    private static final String WALKING_ITEM = "KWEditor.WALKING_ITEM";

    public static KWEditorFragment newInstance(WalkingItem walkingItem) {
        Bundle args = new Bundle();
        args.putSerializable(WALKING_ITEM, walkingItem);
        KWEditorFragment fragment = new KWEditorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    ////

    private WalkingItem mWalkingItem;

    private EditText mTitleEditText;

    public KWEditorFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mWalkingItem = (WalkingItem) getArguments().getSerializable(WALKING_ITEM);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);

        mTitleEditText = (EditText) v.findViewById(R.id.editor_title);
        mTitleEditText.setText(mWalkingItem.getTitle());
        mTitleEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mWalkingItem.setTitle(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        return v;
    }
}
