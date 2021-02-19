package com.example.hdyazilim.randis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Mesaj> {
    private FirebaseUser firebaseUser;
    public CustomAdapter(Context context, ArrayList<Mesaj> chatLists, FirebaseUser firebaseUser){
        super(context,0,chatLists);
        this.firebaseUser=firebaseUser;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        Mesaj mesaj = getItem(position);
        if (firebaseUser.getEmail().equalsIgnoreCase(mesaj.getGönderici()))
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.right_item_layout,parent,false);

            TextView txtUser =(TextView) convertView.findViewById(R.id.txtUserRight);
            TextView txtMesaj =(TextView) convertView.findViewById(R.id.txtMessageRight);
            TextView txtTime =(TextView) convertView.findViewById(R.id.txtTimeRight);

            txtUser.setText(mesaj.getGönderici());
            txtMesaj.setText(mesaj.getMesajText());
            txtTime.setText(mesaj.getZaman());
        }
        else{
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.left_item_layout,parent,false);

            TextView txtUser =(TextView) convertView.findViewById(R.id.txtUserLeft);
            TextView txtMesaj =(TextView) convertView.findViewById(R.id.txtMessageLeft);
            TextView txtTime =(TextView) convertView.findViewById(R.id.txtTimeLeft);

            txtUser.setText(mesaj.getGönderici());
            txtMesaj.setText(mesaj.getMesajText());
            txtTime.setText(mesaj.getZaman());
        }
        return convertView;
    }
}
