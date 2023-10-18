package com.example.ph34050_pnlibary.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.ph34050_pnlibary.DAO.PhieuMuonDAO;
import com.example.ph34050_pnlibary.DAO.SachDAO;
import com.example.ph34050_pnlibary.DAO.ThanhVienDAO;
import com.example.ph34050_pnlibary.Model.PhieuMuon;
import com.example.ph34050_pnlibary.Model.Sach;
import com.example.ph34050_pnlibary.Model.ThanhVien;
import com.example.ph34050_pnlibary.R;
import com.example.ph34050_pnlibary.adapter.PhieuMuonAdapter;
import com.example.ph34050_pnlibary.adapter.SachSpinnerAdapter;
import com.example.ph34050_pnlibary.adapter.ThanhVienSpinnerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class PhieuMuonFragment extends Fragment {
    ListView lv;
    ArrayList<PhieuMuon>list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaPM;
    Spinner spTV,spSach;
    TextView tvNgay,tvTienThue;
    CheckBox chkTraSach;
    Button btnSave, btnCancel;
    static PhieuMuonDAO dao;
    PhieuMuonAdapter adapter;
    PhieuMuon item;
    ThanhVienSpinnerAdapter thanhVienSpinnerAdapter;
    ArrayList<ThanhVien>listThanhVien;
    ThanhVienDAO thanhVienDAO;
    ThanhVien thanhVien;
    int maThanhVien;
    SachSpinnerAdapter sachSpinnerAdapter;
    ArrayList<Sach>listSach;
    SachDAO sachDAO;
    Sach sach;
    int maSach,tienThue;
    int positionTV,positionSach;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_phieu_muon, container, false);
        lv = v.findViewById(R.id.lvPM);
        fab = v.findViewById(R.id.fab);
        dao = new PhieuMuonDAO(getActivity());
        capNhapLv();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(),1);
                return false;
            }
        });
        return v;
    }
    protected void openDialog(final Context context,final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.phieu_muon_dialog);
        edMaPM = dialog.findViewById(R.id.edMaPM);
        spTV = dialog.findViewById(R.id.spTV);
        spSach = dialog.findViewById(R.id.spSach);
        tvNgay = dialog.findViewById(R.id.tvNgay);
        tvTienThue = dialog.findViewById(R.id.tvTienThue);
        chkTraSach = dialog.findViewById(R.id.chkTraSach);
        btnSave = dialog.findViewById(R.id.btnSavePM);
        //set ngay thue mac dinh ngay hien hanh
        tvNgay.setText("Ngày thuê: "+sdf.format(new Date()));

        btnCancel = dialog.findViewById(R.id.btnCancelPM);
        thanhVienDAO = new ThanhVienDAO(context);
        listThanhVien = new ArrayList<ThanhVien>();
        listThanhVien = (ArrayList<ThanhVien>) thanhVienDAO.getAll();
        thanhVienSpinnerAdapter = new ThanhVienSpinnerAdapter(context,listThanhVien);
        spTV.setAdapter(thanhVienSpinnerAdapter);

        spTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maThanhVien = listThanhVien.get(position).getMaTV();
                Toast.makeText(context,"Chọn "+listThanhVien.get(position).getHoTen(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sachDAO = new SachDAO(context);
        listSach = new ArrayList<Sach>();
        listSach = (ArrayList<Sach>) sachDAO.getAll();
        sachSpinnerAdapter = new SachSpinnerAdapter(context,listSach);
        spSach.setAdapter(sachSpinnerAdapter);
        //lấy mã loại sách
        spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSach = listSach.get(position).getMaSach();
                tienThue = listSach.get(position).getGiaThue();
                tvTienThue.setText("Tiền thuê: "+tienThue);
                Toast.makeText(context,"Chọn "+listSach.get(position).getTenSach(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //Kiểm tra type insert 0 hay update 1
        edMaPM.setEnabled(false);
        if(type!=0){
            edMaPM.setText(String.valueOf(item.getMaPM()));
            for(int i =0;i<listThanhVien.size();i++)
                if(item.getMaTV() == (listThanhVien.get(i).getMaTV())){
                    positionTV = i;
                }
            spTV.setSelection(positionTV);
            for(int i =0;i<listSach.size();i++)
                if(item.getMaSach()==(listSach.get(i).getMaSach())){
                    positionSach = i;
                }
            spSach.setSelection(positionSach);
            if(item.getTraSach()==1){
                chkTraSach.setChecked(true);
            }else {
                chkTraSach.setChecked(false);
            }
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new PhieuMuon();
                item.setMaSach(maSach);
                item.setMaTV(maThanhVien);
                item.setNgay(java.sql.Date.valueOf(String.valueOf(LocalDate.now())));
                item.setTienThue(tienThue);
                if(chkTraSach.isChecked()){
                    item.setTraSach(1);
                }else {
                    item.setTraSach(0);
                }
                if(validate()>0){
                    if(type==0){
                        if(type==0){
                            if(dao.insert(item)>0){
                                Toast.makeText(context,"Thêm thành công",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context,"Thêm thất bại",Toast.LENGTH_SHORT).show();
                            }
                        }else {//type = 1 (update)
                            item.setMaPM(Integer.parseInt(edMaPM.getText().toString()));
                            if(dao.update(item)>0){
                                Toast.makeText(context,"Sửa thành công",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context,"Sửa thất bại",Toast.LENGTH_SHORT).show();
                            }
                        }
                        capNhapLv();
                        dialog.dismiss();
                    }
                }

            }
        });
        dialog.show();
    }
    public void xoa(final String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(Id);
                capNhapLv();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        builder.show();
    }
    void capNhapLv(){
        list = (ArrayList<PhieuMuon>) dao.getAll();
        adapter = new PhieuMuonAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
    }
    public int validate(){
        int check = 1;

        return check;
    }
}