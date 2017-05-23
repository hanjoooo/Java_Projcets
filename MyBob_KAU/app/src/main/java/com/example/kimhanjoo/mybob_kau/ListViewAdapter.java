package com.example.kimhanjoo.mybob_kau;

/**
 * Created by kimhanjoo on 17. 1. 14.
 */

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter{
    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<Memos> schedule = null;
    private ArrayList<Memos> arraylist;

    public ListViewAdapter(Context context, List<Memos> worldpopulationlist) {
        mContext = context;
        this.schedule = worldpopulationlist;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Memos>();
        this.arraylist.addAll(worldpopulationlist);
    }

    public class ViewHolder {
        TextView times;
        TextView title;
        TextView memo;
    }

    @Override
    public int getCount() {
        return schedule.size();
    }

    @Override
    public Memos getItem(int position) {
        return schedule.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);
            // Locate the TextViews in listview_item.xml
            holder.times = (TextView) view.findViewById(R.id.rank);
            holder.title = (TextView) view.findViewById(R.id.country);
            holder.memo = (TextView) view.findViewById(R.id.population);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.times.setText(schedule.get(position).getTimes());
        holder.title.setText(schedule.get(position).getTitle());
        holder.memo.setText(schedule.get(position).getMemo());

        // Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(mContext, SingleItemView.class);
                // Pass all data rank
                intent.putExtra("rank",(schedule.get(position).getTimes()));
                // Pass all data country
                intent.putExtra("country",(schedule.get(position).getTitle()));
                // Pass all data population
                intent.putExtra("population",(schedule.get(position).getMemo()));
                // Pass all data flag
                // Start SingleItemView Class
                mContext.startActivity(intent);
            }
        });

        return view;
    }

}
