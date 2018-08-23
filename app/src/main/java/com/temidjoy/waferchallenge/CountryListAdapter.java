package com.temidjoy.waferchallenge;

/**
 * Created by Temidayo Adefioye on 22/08/18.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.MyViewHolder> {
    private Context context;
    private List<Country> countryList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, currency, language;
        public ImageView delete_icon;
        public RelativeLayout viewBackground, viewForeground;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.tvCountry);
            currency = view.findViewById(R.id.tvCurrency);
            language = view.findViewById(R.id.tvLanguage);
            delete_icon=view.findViewById(R.id.delete_icon);
            viewBackground = view.findViewById(R.id.view_background);
            viewForeground = view.findViewById(R.id.view_foreground);
        }
    }


    public CountryListAdapter(Context context, List<Country> cartList) {
        this.context = context;
        this.countryList = cartList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.country_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final Country item = countryList.get(position);
        holder.name.setText(item.getName());
        holder.currency.setText(item.getCurrency());
        holder.language.setText(item.getLanguage());
        holder.delete_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeItem(holder.getAdapterPosition());
            }
        });
        holder.itemView.setTag(item);

    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public void removeItem(int position) {
        countryList.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(Country item, int position) {
        countryList.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }
}
