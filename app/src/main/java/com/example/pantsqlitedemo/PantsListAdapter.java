package com.example.pantsqlitedemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PantsListAdapter extends BaseAdapter {

    private final Context context;
    private final int layout;
    private final ArrayList<Pants> pantsList;

    public PantsListAdapter(Context context, int layout, ArrayList<Pants> pantsList) {
        this.context = context;
        this.layout = layout;
        this.pantsList = pantsList;
    }

    @Override
    public int getCount() {
        return pantsList.size();
    }

    @Override
    public Object getItem(int position) {
        return pantsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class ViewHolder{
        ImageView imageView;
        TextView txtName, txtPrice, txtSize, txtColor;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = new ViewHolder();

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
            holder = (ViewHolder) row.getTag();
        }

        Pants pants = pantsList.get(position);

        holder.txtName.setText(pants.getName());
        holder.txtPrice.setText(pants.getPrice());
        holder.txtSize.setText(pants.getSize());
        holder.txtColor.setText(pants.getColor());


        byte[] pantsImage = pants.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(pantsImage, 0, pantsImage.length);
        holder.imageView.setImageBitmap(bitmap);
        return row;
    }
}


