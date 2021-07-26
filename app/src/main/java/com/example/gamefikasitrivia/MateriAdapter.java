package com.example.gamefikasitrivia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MateriAdapter extends BaseAdapter {

    private final Context context;
    private ArrayList<Materi> materii = new ArrayList<>();

    void setMateri(ArrayList<Materi> materii) {
        this.materii = materii;
    }

    MateriAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return materii.size();
    }

    @Override
    public Object getItem(int i) {
        return materii.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view;
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_materi, viewGroup, false);
        }
        ViewHolder viewHolder = new ViewHolder(itemView);
        Materi materi = (Materi) getItem(i);
        viewHolder.bind(materi);
        return itemView;
    }

    private static class ViewHolder {
        private TextView txtName;
        private TextView txtDescription;
        private CircleImageView imgPhoto;

        ViewHolder(View view) {
            txtName = view.findViewById(R.id.txt_name);
            txtDescription = view.findViewById(R.id.txt_description);
            imgPhoto = view.findViewById(R.id.img_photo);
        }

        void bind(Materi materi) {
            txtName.setText(materi.getName());
            txtDescription.setText(materi.getDescription());
            imgPhoto.setImageResource(materi.getPhoto());
        }
    }
}