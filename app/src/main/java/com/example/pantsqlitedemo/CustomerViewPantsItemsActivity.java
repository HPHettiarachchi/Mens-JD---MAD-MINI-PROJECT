package com.example.pantsqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomerViewPantsItemsActivity extends AppCompatActivity {

        GridView gridView;
        String[] names = {"Normal Denim 1:","Normal Denim 2: ","Normal Denim 3: ","Normal Denim 4: ","Normal Denim 5: ","Normal Denim 6: ","Normal Denim 7:","Normal Denim 8:","Normal Denim 9:","Normal Denim 10:","Normal Denim 11:","Normal Denim 12:","Normal Denim 13:","Normal Denim 14:","Normal Denim 15:"," Patch Denim 1:","Patch Denim 2:","Patch Denim 3:","Patch Denim 4:","Patch Denim 5:","Patch Denim 6:","Casual 1:","Casual 2:","Casual 3:","Casual 4:",};
        String[] prices ={"1400/=","1400/=","1400/=","1400/=","1400/=","1400/=","1400/=","1400/=","1400/=","1400/=","1400/=","1400/=","1400/=","1400/=","1400/=","1400/=","1400/=","1400/=","1400/=","1400/=","1400/=","1400/=","1400/=","1400/=","1400/="};
        String[] sizes = {"28","30","32","34","36","38","40","28","30","32","34","36","38","40","28","28","30","32","34","36","38","28","30","32","34"};
        String[] colors = {"Black Color","Bark Blue","Bark Blue","Bark Blue","Bark Blue","Bark Blue","Bark Blue","Bark Blue","Black","Black","Black","Black","Black","Black","Black","Black","Black","Black","Light Blue","Light Blue","Light Blue","Light Blue","Light Blue","Light Blue","Light Blue","Light Blue","Light Blue","Light Blue","Yellow","Yellow","Yellow","Yellow"};
        int[] images = {R.drawable.denim1,R.drawable.denim2,R.drawable.denim3,R.drawable.denim4,R.drawable.denim5,R.drawable.denim6,R.drawable.denim7,R.drawable.denim8,R.drawable.denim9,R.drawable.denim10,R.drawable.denim10,R.drawable.denim11,R.drawable.denim12,R.drawable.denim13,R.drawable.denim14,R.drawable.denim15,R.drawable.patch1,R.drawable.patch2,R.drawable.patch3,R.drawable.patch4,R.drawable.patch5,R.drawable.patch6,R.drawable.casual1,R.drawable.casual2,R.drawable.casual3,R.drawable.casual4};
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view_pants_items);

        gridView = findViewById(R.id.graidViewType);

        CustomAdapter customAdapter = new CustomAdapter(names, prices, sizes, colors, images, this);

        gridView.setAdapter(customAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
@Override
public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String selectedName = names[position];
        String selectedPrice = prices[position];
        String selectedSize = sizes[position];
        String selectedColor = colors[position];

        int selectedImage = images[position];

        startActivity(new Intent(CustomerViewPantsItemsActivity.this,ClickedItemActivity.class).putExtra("name", selectedName).putExtra("price", selectedPrice).putExtra("size", selectedSize).putExtra("color", selectedColor).putExtra("image",selectedImage));

        }
        });

        }

public class CustomAdapter extends BaseAdapter {
    private String[] imageName;
    private String[] imagePrice;
    private String[] imageSize;
    private String[] imageColor;
    private int[] imagesPhoto;
    private Context context;
    private LayoutInflater layoutInflater;

    public CustomAdapter(String[] imageName,String[] imagePrice,String[] imageSize,String[] imageColor, int[] imagesPhoto, Context context) {
        this.imageName = imageName;
        this.imagePrice = imagePrice;
        this.imageSize = imageSize;
        this.imageColor = imageColor;
        this.imagesPhoto = imagesPhoto;
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return imagesPhoto.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.row_items, parent, false);
        }

        TextView txtName = convertView.findViewById(R.id.txtName);
        TextView txtPrice = convertView.findViewById(R.id.txtPrice);
        TextView txtSize = convertView.findViewById(R.id.txtSize);
        TextView txtColor = convertView.findViewById(R.id.txtColor);

        ImageView imagePhoto = convertView.findViewById(R.id.imageView);

        txtName.setText(imageName[position]);
        txtPrice.setText(imagePrice[position]);
        txtSize.setText(imageSize[position]);
        txtColor.setText(imageColor[position]);
        imagePhoto.setImageResource(imagesPhoto[position]);


        return convertView;
    }
}
}