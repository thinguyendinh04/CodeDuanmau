package com.example.ph34050_pnlibary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ph34050_pnlibary.Fragment.TopFragment;
import com.example.ph34050_pnlibary.Model.Top;
import com.example.ph34050_pnlibary.R;

import java.util.ArrayList;

public class TopAdapter extends ArrayAdapter<Top> {
    private Context context;
    TopFragment fragment;
    private ArrayList<Top>lists;
    TextView tvSach,tvSL;
    ImageView imgDel;

    public TopAdapter(@NonNull Context context, TopFragment fragment,ArrayList<Top>lists) {
        super(context, 0,lists);
        this.context = context;
        this.fragment = fragment;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.top_item,null);
        }
        final Top item = lists.get(position);
        if(item!=null){
            tvSach = v.findViewById(R.id.tvSach);
            tvSach.setText("Sách: "+item.getTenSach());
            tvSL = v.findViewById(R.id.tvSL);
            tvSL.setText("Sách: "+item.getSoLuong());
        }
        return v;
    }
}
