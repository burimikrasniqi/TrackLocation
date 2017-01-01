package net.zagzag.tracklocation.View.MainView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.zagzag.tracklocation.Model.UserData;
import net.zagzag.tracklocation.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by burim on 12/27/2016.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private ArrayList<UserData> mDataset;

    public RecyclerAdapter() {
        this.mDataset = new ArrayList<>();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView title;
        public TextView description;
        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.text);
            description = (TextView) itemView.findViewById(R.id.description);
        }
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_items, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        holder.title.setText(mDataset.get(position).getTitle());
        holder.description.setText(mDataset.get(position).getDescripion());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public void addAllAndClear(List<UserData> list){
        mDataset.clear();
        mDataset.addAll(list);
        notifyDataSetChanged();
    }
    public void addAll(List<UserData> list){
        mDataset.addAll(list);
        notifyDataSetChanged();
    }

}
