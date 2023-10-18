package com.example.ph34050_pnlibary.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.ph34050_pnlibary.DAO.LoaiSachDAO;
import com.example.ph34050_pnlibary.DAO.SachDAO;
import com.example.ph34050_pnlibary.Model.LoaiSach;
import com.example.ph34050_pnlibary.Model.Sach;
import com.example.ph34050_pnlibary.R;
import com.example.ph34050_pnlibary.adapter.LoaiSachSpinnerAdapter;
import com.example.ph34050_pnlibary.adapter.sachAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SachFragment extends Fragment {
    ListView lv;
    ArrayList<Sach>list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaSach,edTenSach,edGiaThue;
    Spinner spinner;
    Button btnSave , btnCancel;
    static SachDAO dao;
    sachAdapter adapter;
    Sach item;
    LoaiSachSpinnerAdapter spinnerAdapter;
    ArrayList<LoaiSach>listLoaiSach;
    LoaiSachDAO loaiSachDAO;
    LoaiSach loaiSach;
    int maLoaiSach,position;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sach, container, false);
        lv = v.findViewById(R.id.lvSach);
        fab = v.findViewById(R.id.fab);
        dao = new SachDAO(getActivity());
        capNhatLv();
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
                openDialog(getActivity(),1);//update
                return false;
            }
        });

        return v;
    }
    protected void openDialog(final Context context,final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.sach_dialog);
        edMaSach = dialog.findViewById(R.id.edMaSach);
        edTenSach = dialog.findViewById(R.id.edTenSach);
        edGiaThue = dialog.findViewById(R.id.edGiaThue);
        spinner = dialog.findViewById(R.id.spLoaiSach);
        btnCancel = dialog.findViewById(R.id.btnCancelSach);
        btnSave = dialog.findViewById(R.id.btnSaveSach);
        listLoaiSach = new ArrayList<LoaiSach>();
        loaiSachDAO = new LoaiSachDAO(context);
        listLoaiSach =(ArrayList<LoaiSach>) loaiSachDAO.getAll();
        spinnerAdapter = new LoaiSachSpinnerAdapter(context,listLoaiSach);
        spinner.setAdapter(spinnerAdapter);
        //lay ma loai sach
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoaiSach = listLoaiSach.get(position).getMaLoai();
                Toast.makeText(context,"chon"+listLoaiSach.get(position).getTenLoai(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        edMaSach.setEnabled(false);
        if(type!=0) {
            edMaSach.setText(String.valueOf(item.getMaSach()));
            edTenSach.setText(item.getTenSach());
            edGiaThue.setText(String.valueOf(item.getGiaThue()));
            for (int i = 0; i < listLoaiSach.size(); i++) {
                if (item.getMaLoai() == (listLoaiSach.get(i).getMaLoai())) {
                    position = i;
                }
                Log.i("demo", "posSach" + position);
                spinner.setSelection(position);
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
                    item = new Sach();
                    item.setTenSach(edTenSach.getText().toString());
                    item.setGiaThue(Integer.parseInt(edGiaThue.getText().toString()));
                    item.setMaLoai(maLoaiSach);
                    if(validate()>0){
                        if(type==0){
                            if(dao.insert(item)>0){
                                Toast.makeText(context,"Thêm thành công",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context,"Thêm thất bại",Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            //type=1 update
                            item.setMaSach(Integer.parseInt(edMaSach.getText().toString()));
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
    public void xoa(final String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(Id);
                capNhatLv();
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
    void capNhatLv(){
        list = (ArrayList<Sach>) dao.getAll();
        adapter = new sachAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
    }
    public int validate(){
        int check = 1;
        if(edTenSach.getText().length()==0){
            Toast.makeText(getContext(),"Vui lòng nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}