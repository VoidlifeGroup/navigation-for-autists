package com.ibm.mysampleapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Slúži na vypisovanie Arraylistu do ListViewu avšak ešte nefunguje, po implementovaní appka nepadne
 * avšak nič nevypíše. Pri implementovani ArrayAdapteru sa vypíšu len adresy, preto je potrebny
 * customAdapter ktorým vieme pozmeniť čo chceme a ako to chceme vypísať
 */

public class CustomAdapter extends ArrayAdapter {
    private Context mContext;
    private ArrayList<Building> mList;

    CustomAdapter(@NonNull Context context, @NonNull ArrayList<Building> objects) {
        super(context, R.layout.list_names);
            mContext = context;
            mList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        //TODO z nejakeho dôvodu nefunguje pri jeho použití

        View view;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert inflater != null;
            view = inflater.inflate(R.layout.list_names, null);
        }
        else {
            view = convertView;
        }

        TextView names = (TextView) view.findViewById(R.layout.list_names);

        names.setText( mList.get(position).getName() );
        return view;
    }
}
