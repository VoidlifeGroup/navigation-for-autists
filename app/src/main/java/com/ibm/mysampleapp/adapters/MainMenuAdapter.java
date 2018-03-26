package com.ibm.mysampleapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ibm.mysampleapp.R;
import com.ibm.mysampleapp.core.activities.MainActivity;

import java.util.ArrayList;

/**
 * Created by mato on 18. 3. 2018.
 */

public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.ViewHolder> {
    private ArrayList<String> mDataset;
    private Context mContext;



    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // add data items
        public TextView mTextView;
        public ConstraintLayout parentLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            this.mTextView = (TextView) itemView.findViewById(R.id.menuItem);
            this.parentLayout = (ConstraintLayout) itemView.findViewById(R.id.parentLayout);
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MainMenuAdapter(Context context, ArrayList<String> myDataset) {
        this.mContext = context;
        this.mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MainMenuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_menu_list, parent, false);

        return new ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position));
        holder.parentLayout.setOnClickListener(view -> {

            Intent intent;

            switch (holder.getAdapterPosition()){
                case 0:
                    intent = new Intent(mContext, MainActivity.class);
                    mContext.startActivity(intent);
                    break;
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
