package com.example.jeansshoppingcart.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jeansshoppingcart.Model.CartModel;
import com.example.jeansshoppingcart.OrderDBHelper;
import com.example.jeansshoppingcart.R;

import java.util.ArrayList;
import java.util.List;

public class NewCartAdapter extends RecyclerView.Adapter<NewCartAdapter.ViewHolder> {

    ArrayList<CartModel> list;
    Context context;

    public NewCartAdapter(ArrayList<CartModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_sample_item , parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CartModel model = list.get(position);

        //get the particular position
        //holder.imageView.setImageResource(model.getImage());
        holder.txtName.setText(model.getName());
        holder.txtPrice.setText(model.getPrice());
        holder.txtQuantity.setText(model.getQuantity());
        holder.txtSize.setText(model.getSize());
        holder.txtColor.setText(model.getColor());

        //delete order items from database
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(context)
                    .setTitle("Delete Item")
                    .setMessage("Are you sure to delete this item?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            OrderDBHelper helper = new OrderDBHelper(context);
                            if(helper.deleteOrder(model.getId()) > 0) {
                                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context, "Not Deleted", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    }).show();
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        //here count Model class data
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageView;
        public TextView txtName,  txtPrice, txtQuantity, txtSize, txtColor;
        ImageView btnDelete;


        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);
            txtQuantity = (TextView) itemView.findViewById(R.id.txtQuantity);
            txtSize = (TextView) itemView.findViewById(R.id.txtSize);
            txtColor = (TextView) itemView.findViewById(R.id.txtColor);
            btnDelete = (ImageView) itemView.findViewById(R.id.btnDelete);

        }
    }
}
