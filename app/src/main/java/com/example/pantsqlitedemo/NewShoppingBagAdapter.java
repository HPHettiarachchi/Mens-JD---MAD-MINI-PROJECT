package com.example.jeansshoppingcart.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jeansshoppingcart.AddBagItem;
import com.example.jeansshoppingcart.Model.MainModel;
import com.example.jeansshoppingcart.R;

import java.util.ArrayList;

public class NewShoppingBagAdapter extends RecyclerView.Adapter<NewShoppingBagAdapter.viewholder>{

    ArrayList<MainModel> list;
    Context context;
    Button AddCart;

    public NewShoppingBagAdapter(ArrayList<MainModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shopping_bag_sample_item , parent , false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(viewholder holder, int position) {
        final MainModel model = list.get(position);
        holder.jeanimage.setImageResource(model.getImage());
        holder.mainName.setText(model.getName());
        holder.price.setText(model.getPrice());

    }

    @Override
    public int getItemCount() {
        //this gets the total count of the product in the array
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView jeanimage;
        TextView mainName, price;

        public viewholder(View itemView) {
            super(itemView);

            jeanimage = itemView.findViewById(R.id.imageView);
            mainName = itemView.findViewById(R.id.Name);
            price = itemView.findViewById(R.id.orderPrice);
            AddCart = itemView.findViewById(R.id.AddCartBtn);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            if (position == 0){
                Intent intent = new Intent(context, AddBagItem.class);
                context.startActivity(intent);
            }
        }
    }
}
