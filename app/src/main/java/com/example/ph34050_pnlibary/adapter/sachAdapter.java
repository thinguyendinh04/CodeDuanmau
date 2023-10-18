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

import com.example.ph34050_pnlibary.DAO.LoaiSachDAO;
import com.example.ph34050_pnlibary.Fragment.SachFragment;
import com.example.ph34050_pnlibary.Model.LoaiSach;
import com.example.ph34050_pnlibary.Model.Sach;
import com.example.ph34050_pnlibary.R;

import java.util.ArrayList;

public class sachAdapter extends ArrayAdapter<Sach> {
    private Context context;
    SachFragment fragment;
    private ArrayList<Sach>lists;
    TextView tvMaSach,tvTenSach,tvGiaThue,tvLoai;
    ImageView imgDel;

    public sachAdapter(@NonNull Context context, SachFragment fragment, ArrayList<Sach> lists) {
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
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v= inflater.inflate(R.layout.sach_item,null);
        }
        final Sach item = lists.get(position);
        if(item!=null){
            LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
            LoaiSach loaiSach = loaiSachDAO.getid(String.valueOf(item.getMaLoai()));
            tvMaSach = v.findViewById(R.id.tvMaSach);
            tvMaSach.setText("Mã Sách: "+item.getMaSach());
            tvTenSach = v.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên Sách: "+item.getTenSach());
            tvGiaThue = v.findViewById(R.id.tvGiaThue);
            tvGiaThue.setText("Giá Thuê: "+item.getGiaThue());
            tvLoai = v.findViewById(R.id.tvLoai);
            tvLoai.setText("Loại Sách: "+loaiSach.getTenLoai());
            imgDel = v.findViewById(R.id.imgDel);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               fragment.xoa(String.valueOf(item.getMaSach()));
            }
        });
        return v;
    }
}
