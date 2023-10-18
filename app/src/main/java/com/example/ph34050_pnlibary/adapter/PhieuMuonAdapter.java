package com.example.ph34050_pnlibary.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ph34050_pnlibary.DAO.SachDAO;
import com.example.ph34050_pnlibary.DAO.ThanhVienDAO;
import com.example.ph34050_pnlibary.Fragment.PhieuMuonFragment;
import com.example.ph34050_pnlibary.Model.PhieuMuon;
import com.example.ph34050_pnlibary.Model.Sach;
import com.example.ph34050_pnlibary.Model.ThanhVien;
import com.example.ph34050_pnlibary.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuon> {
    private Context context;
    PhieuMuonFragment fragment;
    private ArrayList<PhieuMuon>lists;
    TextView tvMaPM,tvTenTV,tvTenSach,tvTienThue,tvNgay,tvTraSach;
    ImageView imgDel;
    SachDAO sachDAO;
    ThanhVienDAO thanhVienDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public PhieuMuonAdapter(@NonNull Context context,PhieuMuonFragment fragment,ArrayList<PhieuMuon>lists) {
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
            v = inflater.inflate(R.layout.phieu_muon_item,null);
        }
        final PhieuMuon item = lists.get(position);
        if(item!=null){
            tvMaPM = v.findViewById(R.id.tvMaPM);
            tvMaPM.setText("Mã phiếu: "+item.getMaPM());
            sachDAO = new SachDAO(context);
            Sach sach = sachDAO.getid(String.valueOf(item.getMaSach()));
            tvTenSach = v.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên sách: "+sach.getTenSach());
            thanhVienDAO = new ThanhVienDAO(context);
            ThanhVien thanhVien = thanhVienDAO.getid(String.valueOf(item.getMaTV()));
            tvTenTV = v.findViewById(R.id.tvTenTV);
            tvTenTV.setText("Thành viên: "+thanhVien.getHoTen());
            tvTienThue = v.findViewById(R.id.tvTienThue);
            tvTienThue.setText("Tiền Thuê: "+item.getTienThue());
            tvNgay = v.findViewById(R.id.tvNgay);
            tvNgay.setText("Ngày thuê: "+sdf.format(item.getNgay()));
            tvTraSach = v.findViewById(R.id.tvTraSach);
            if(item.getTraSach()==1){
                tvTraSach.setTextColor(Color.BLUE);
                tvTraSach.setText("Đã trả sách");
            }else {
                tvTraSach.setTextColor(Color.RED);
                tvTraSach.setText("Chưa trả sách");
            }
            imgDel = v.findViewById(R.id.imgDeleteLS);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.getMaPM()));
            }
        });
        return v;
    }
}
