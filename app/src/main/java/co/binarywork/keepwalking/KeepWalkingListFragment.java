package co.binarywork.keepwalking;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wind on 7/26/2016 AD.
 */
public class KeepWalkingListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    private List<WalkingItem> mWalkingItemList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.keep_walking_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mWalkingItemList = new ArrayList<>();
        mWalkingItemList.add(new WalkingItem("Sample #1"));
        mWalkingItemList.add(new WalkingItem("Sample #2"));
        mWalkingItemList.add(new WalkingItem("Sample #3"));

        updateUI();

        return v;
    }

    private void updateUI() {
        if(mAdapter == null) {
            mAdapter = new KWViewAdapter();
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    protected class KWViewHolder extends RecyclerView.ViewHolder {

        TextView mTitleText;
        TextView mDateText;

        public KWViewHolder(View itemView) {
            super(itemView);

            mTitleText = (TextView) itemView.findViewById(R.id.item_card_title);
            mDateText = (TextView) itemView.findViewById(R.id.item_card_date);
        }

        public void refreshViewBy(WalkingItem walkingItem) {
            mTitleText.setText(walkingItem.getTitle());
            mDateText.setText(walkingItem.getDate().toString());
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
            holder.refreshViewBy(mWalkingItemList.get(position));
        }

        @Override
        public int getItemCount() {
            return mWalkingItemList.size();
        }
    }
}
