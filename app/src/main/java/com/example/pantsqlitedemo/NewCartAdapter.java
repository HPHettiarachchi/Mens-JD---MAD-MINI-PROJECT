package com.example.jeansshoppingcart.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jeansshoppingcart.Model.CartModel;
import com.example.jeansshoppingcart.R;

import java.util.List;

public class NewCartAdapter extends RecyclerView.Adapter<NewCartAdapter.ViewHolder> {

    List<CartModel> listModel;
    Context context;

    public NewCartAdapter(List<CartModel> listModel, Context context) {
        this.listModel = listModel;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cart_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CartModel cartModel = listModel.get(position);

        //get the particular position
        holder.imageView.setImageResource(cartModel.getImage());
        holder.txtName.setText(cartModel.getName());
        holder.txtPrice.setText(cartModel.getPrice());
        holder.txtQuantity.setText(cartModel.getQuantity());

    }

    @Override
    public int getItemCount() {
        //here count Model class data
        return listModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView txtName,  txtPrice, txtQuantity;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);
            txtQuantity = (TextView) itemView.findViewById(R.id.txtQuantity);

        }
    }
}
