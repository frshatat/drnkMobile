package com.drnkmobile.drnkAndroid.drnk.Customize;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.drnkmobile.drnkAndroid.app.R;

import java.util.List;

/**
 * Created by FarisShatat on 10/4/15.
 */
public class CustomListView extends ArrayAdapter<String> {

    private Context context;
    private List<String> businessList;
    private List<String>specialList;
    private List<String>idList;
    private List<String>listOfAddress;

    public CustomListView(Context context, int resource, List<String> objects, List specialList, List idList, List<String> listOfAddress){
        super(context,resource,objects);
        this.context = context;
        this.businessList = objects;
        this.specialList = specialList;
        this.idList = idList;
        this.listOfAddress=listOfAddress;
    }

    @Override
    public View getView ( int position, View convertView, ViewGroup parent){
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.items_main_tableview, null);
        }
            if(position<listOfAddress.size()) {

                String business = businessList.get(position);

               final String special = specialList.get(position);
                String address = listOfAddress.get(position);

                ImageView image = (ImageView) convertView.findViewById(R.id.imageView);
                image.setImageResource(generateImage(idList.get(position)));

                TextView businessName = (TextView) convertView.findViewById(R.id.businessNameTextView);
                TextView specials = (TextView) convertView.findViewById(R.id.specialsTextView);
                Typeface face = Typeface.createFromAsset(convertView.getContext().getAssets(), "fonts/AvenirLTStd-Light.ttf");
                businessName.setText(business);
                specials.setText(special);
                businessName.setTypeface(face);
                specials.setTypeface(face);
                TextView addressText = (TextView) convertView.findViewById(R.id.address);
                addressText.setText(address);

            }
        return convertView;

    }


    public int generateImage(String id){

        String imageString = id;
        int businessImage = R.drawable.ic_logo;

        switch (imageString) {

            case "35":
                businessImage = R.drawable.brothers;
                break;

            case "43":

                businessImage = R.drawable.savages;
                break;

            case "45":

                businessImage = R.drawable.be_here_now;
                break;

            case "44":

                businessImage = R.drawable.the_chug;
                break;

            case "48":

                businessImage = R.drawable.scottys;
                break;

            case "34":

                businessImage = R.drawable.friendly_package;
                break;

            case "42":

                businessImage = R.drawable.munice_liquors;
                break;

            case "57":

                businessImage = R.drawable.saveonliquor;
                break;

            case "58":
                businessImage = R.drawable.saveonliquor;
                break;

            case "40":

                businessImage = R.drawable.heorot;
                break;

            case "49":

                businessImage = R.drawable.paraiso;
                break;

            case "47":

                businessImage = R.drawable.amazingjoes;
                break;

            case "46":

                businessImage = R.drawable.ficklepeach;
                break;


            case "0000":

                businessImage = R.drawable.ic_logo;
                break;


        }

        return businessImage;

    }
}

