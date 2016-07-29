package co.binarywork.keepwalking;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.binarywork.keepwalking.model.WalkingItem;
import co.binarywork.keepwalking.model.WalkingItemLab;

/**
 * Created by wind on 7/26/2016 AD.
 */
public class KeepWalkingListFragment extends Fragment {

    private static final String LEFT_POSITION = "KeepWalkingListFragment.LEFT_POSITION";
    private RecyclerView _mainRecyclerView;
    private RecyclerView.Adapter _adapter;
    private List<WalkingItem> _walkingItemList;

    private Integer _leftPosition;

    public static KeepWalkingListFragment newInstance() {
        Bundle args = new Bundle();

        KeepWalkingListFragment fragment = new KeepWalkingListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);

        _mainRecyclerView = (RecyclerView) v.findViewById(R.id.keep_walking_recycler_view);
        _mainRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        _walkingItemList = WalkingItemLab.getInstance(getActivity()).getWalkingItemList();

        _walkingItemList.add(new WalkingItem("Sample #1"));
        _walkingItemList.add(new WalkingItem("Sample #2"));
        _walkingItemList.add(new WalkingItem("Sample #3"));

        updateUI();

        return v;
    }

    private void updateUI() {
        if(_adapter == null) {
            _adapter = new KWViewAdapter();
            _mainRecyclerView.setAdapter(_adapter);
        } else {
            _adapter.notifyDataSetChanged(); // update all list in the screen
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(LEFT_POSITION, _leftPosition);
    }

    protected class KWViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mTitleText;
        TextView mDateText;

        Integer mPosition;

        public KWViewHolder(View itemView) {
            super(itemView);

            mTitleText = (TextView) itemView.findViewById(R.id.item_card_title);
            mDateText = (TextView) itemView.findViewById(R.id.item_card_date);

            itemView.setOnClickListener(this);
        }

        public void refreshViewBy(int currentIndex) {
            WalkingItem walkingItem = WalkingItemLab.getInstance(getActivity()).getWalkingItem(currentIndex);

            mPosition = currentIndex;
            mTitleText.setText(walkingItem.getTitle());
            mDateText.setText(walkingItem.getDate().toString());
        }

        @Override
        public void onClick(View view) {
            Intent intent = KWEditorActivity.newIntent(getActivity(), mPosition );
            _leftPosition = mPosition;
            startActivity(intent);
        }
    }

    protected class KWViewAdapter extends RecyclerView.Adapter<KWViewHolder> {

        @Override
        public KWViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View v = layoutInflater.inflate(R.layout.item_card, parent, false);

            return new KWViewHolder(v);
        }

        @Override
        public void onBindViewHolder(KWViewHolder holder, int position) {
            holder.refreshViewBy(position);
        }

        @Override
        public int getItemCount() {
            return _walkingItemList.size();
        }

    }
}
