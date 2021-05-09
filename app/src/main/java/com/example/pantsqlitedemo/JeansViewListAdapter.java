package com.example.pantsqlitedemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class JeansViewListAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<Jeans> jeansViewList;
    @Override
    public int getCount() {
        return jeansViewList.size();
    }

    public JeansViewListAdapter(Context context, int layout, ArrayList<Jeans> jeansViewList) {
        this.context = context;
        this.layout = layout;
        this.jeansViewList = jeansViewList;
    }

    @Override
    public Object getItem(int position) {
        return jeansViewList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtName, txtPrice, txtSize, txtColor;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        JeansViewListAdapter.ViewHolder holder = new JeansViewListAdapter.ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtName = (TextView) row.findViewById(R.id.txtName);
            holder.txtPrice = (TextView) row.findViewById(R.id.txtPrice);
            holder.txtSize = (TextView) row.findViewById(R.id.txtSize);
            holder.txtColor = (TextView) row.findViewById(R.id.txtColor);
            holder.imageView = (ImageView) row.findViewById(R.id.imgPant);
            row.setTag(holder);
        }
        else{
            holder = (JeansViewListAdapter.ViewHolder) row.getTag();
        }

        Jeans jeans = jeansViewList.get(position);

        holder.txtName.setText(jeans.getName());
        holder.txtPrice.setText(jeans.getPrice());
        holder.txtSize.setText(jeans.getSize());
        holder.txtColor.setText(jeans.getColor());
        byte[] jeansImage = jeans.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(jeansImage, 0, jeansImage.length);
        holder.imageView.setImageBitmap(bitmap);
        return row;
    }

}
