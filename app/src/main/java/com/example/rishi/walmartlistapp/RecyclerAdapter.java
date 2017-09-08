package com.example.rishi.walmartlistapp;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rishi.walmartlistapp.Model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by rishi on 9/7/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {


    private List<Product> products;
    private Context context;



    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    private OnLoadMoreListener mOnLoadMoreListener;
    private boolean isLoading = true;
    private  boolean isDataAvailable = true;
    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;

    public RecyclerAdapter(Context context, List<Product> products){
        this.products = products;
        this.context = context;
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(VIEW_TYPE_ITEM == viewType) {


            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
            return new ItemViewHolder(view);
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        if(position>=getItemCount()-1 && isDataAvailable && !isLoading && mOnLoadMoreListener!=null){
            isLoading = true;
            mOnLoadMoreListener.onLoadMore();
        }

        if(getItemViewType(position)==VIEW_TYPE_ITEM){

            String productName = products.get(position).getProductName();
            if(productName.length()>31){
                holder.name.setText(productName.substring(0,31).concat("..."));
            }


            else {
                holder.name.setText(productName);
            }

            holder.price.setText(products.get(position).getPrice());
            Uri uri = Uri.parse(products.get(position).getProductImage().toString());
            Picasso.with(context).load(uri).resize(80,80).centerCrop().into(holder.image);
            Log.d("Image",products.get(position).getProductImage().toString() );
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView price;
        ImageView image;

        public ItemViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.nameTv);
            price = (TextView) itemView.findViewById(R.id.priceTv);
            image = (ImageView) itemView.findViewById(R.id.itemIv);


        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {
        public ProgressBar progressBar;

        public LoadingViewHolder(View view) {
            super(view);
            progressBar = (ProgressBar) view.findViewById(R.id.progressBar1);
        }
    }
}
