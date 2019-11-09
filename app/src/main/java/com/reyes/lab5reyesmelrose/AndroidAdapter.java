package com.reyes.lab5reyesmelrose;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AndroidAdapter extends ArrayAdapter<AndroidCom> {
    private Context context;
    private int resource;

    public AndroidAdapter(Context context, int resource, List<AndroidCom> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        // return super.getView(position, convertView, parent);
        int logo = getItem(i).getLogo();

        String com = getItem(i).getName();
        String country = getItem(i).getCoun();
        String industry = getItem(i).getInd();
        String chief = getItem(i).getCeo();

        LayoutInflater inflater = LayoutInflater.from(context);

        convertView = inflater.inflate(resource, parent, false);

        ImageView img = convertView.findViewById(R.id.imageView);

        TextView comName = convertView.findViewById(R.id.tv1);
        TextView counName = convertView.findViewById(R.id.tv2);
        TextView indName = convertView.findViewById(R.id.tv3);
        TextView ceoName = convertView.findViewById(R.id.tv4);

        img.setImageResource(logo);

        comName.setText(com);
        counName.setText(country);
        indName.setText(industry);
        ceoName.setText(chief);

        return convertView;
    }
}
