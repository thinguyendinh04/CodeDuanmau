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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.ph34050_pnlibary.DAO.LoaiSachDAO;
import com.example.ph34050_pnlibary.Model.LoaiSach;
import com.example.ph34050_pnlibary.R;
import com.example.ph34050_pnlibary.adapter.LoaiSachAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class LoaiSachFragment extends Fragment {
    ListView lvLoaiSach;
    ArrayList<LoaiSach>list;
    static LoaiSachDAO dao;
    LoaiSachAdapter adapter;
    LoaiSach item;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaLS,edTenLS;
    Button btnSave,btnCancel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_loai_sach, container, false);
        lvLoaiSach = v.findViewById(R.id.lvLoaiSach);
        fab = v.findViewById(R.id.fab);
        dao = new LoaiSachDAO(getActivity());
        capNhatLv();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiaLog(getActivity(),0);
            }
        });
        lvLoaiSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDiaLog(getActivity(),1); //update
                return false;
            }
        });
        return v;
    }
    void capNhatLv(){
        list = (ArrayList<LoaiSach>) dao.getAll();
        adapter = new LoaiSachAdapter(getActivity(),this,list);
        lvLoaiSach.setAdapter(adapter);
    }
    public void xoa(final String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có chắc chắn muôn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //gọi delete
                        dao.delete(Id);
                        //cập nhật
                        capNhatLv();
                        dialog.cancel();
                    }
                });
        builder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        builder.show();

    }
    protected void openDiaLog(final Context context,final int type){
        //custom dialog
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.loai_sach_dialog);
        edMaLS = dialog.findViewById(R.id.edMaLS);
        edTenLS = dialog.findViewById(R.id.edTenLS);
        btnSave = dialog.findViewById(R.id.btnSaveLS);
        btnCancel = dialog.findViewById(R.id.btnCancelLS);
        //Kiểm tra type insert 0 hay 1
        edMaLS.setEnabled(false);
        if(type!=0){
            edMaLS.setText(String.valueOf(item.getMaLoai()));
            edTenLS.setText(item.getTenLoai());
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
                item = new LoaiSach();
                item.setTenLoai(edTenLS.getText().toString());
                if(validate()>0){
                    if(type==0){
                        //type = 0 insert
                        if(dao.insert(item)>0){
                            Toast.makeText(context,"Thêm thành công",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,"Thêm thất bại",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        //type = 1 update
                        item.setMaLoai(Integer.parseInt(edMaLS.getText().toString()));
                        if(dao.update(item)>0){
                            Toast.makeText(context,"Sửa thành công",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,"Sửa thất bại",Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }
    public int validate(){
        int check = 1;
        if(edTenLS.getText().toString().length()==0){
            Toast.makeText(getContext(),"Vui lòng nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}