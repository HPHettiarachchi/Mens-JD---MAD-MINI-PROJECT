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
import com.example.jeansshoppingcart.InfoCartActivity;
import com.example.jeansshoppingcart.Model.MainModel;
import com.example.jeansshoppingcart.NewShoppingBag;
import com.example.jeansshoppingcart.R;

import java.util.ArrayList;

public class NewShoppingBagAdapter extends RecyclerView.Adapter<NewShoppingBagAdapter.viewholder>{

    ArrayList<MainModel> list;
    Context context;

    public NewShoppingBagAdapter(ArrayList<MainModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        //here get layout
        View view = LayoutInflater.from(context).inflate(R.layout.shopping_bag_sample_item , parent , false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(final viewholder holder, int position) {

        //here we will find the position and
        final MainModel model = list.get(position);
        holder.jeanimage.setImageResource(list.get(position).getImage());
        holder.mainName.setText(list.get(position).getName());
        holder.price.setText("RS"+list.get(position).getPrice());
        holder.color.setText(list.get(position).getColor());
        holder.size.setText(list.get(position).getSize());

        holder.jeanimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InfoCartActivity.class);
                intent.putExtra("jeanimage",model.getImage());
                intent.putExtra("mainName", model.getName());
                intent.putExtra("size",model.getSize());
                intent.putExtra("price", model.getPrice());
                intent.putExtra("color", model.getColor());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        //this gets the total count of the product in the array
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        ImageView jeanimage;
        TextView mainName, price, color, size;


        public viewholder(View itemView) {
            super(itemView);

            jeanimage = itemView.findViewById(R.id.imageView);
            mainName = itemView.findViewById(R.id.Name);
            price = itemView.findViewById(R.id.orderPrice);
            color = itemView.findViewById(R.id.orderColor);
            size = itemView.findViewById(R.id.orderSize);


        }



    }
}
