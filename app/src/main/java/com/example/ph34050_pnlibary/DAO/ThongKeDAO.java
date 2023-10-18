package com.example.ph34050_pnlibary.DAO;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ph34050_pnlibary.Model.Sach;
import com.example.ph34050_pnlibary.Model.Top;
import com.example.ph34050_pnlibary.database.DbHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDAO {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public ThongKeDAO(Context context){
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    //Thống kê top 10
    @SuppressLint("Range")
    public List<Top> getTop(){
        String sqlTop = "SELECT maSach, count(maSach) as soLuong FROM PhieuMuon GROUP BY maSach ORDER BY soLuong DESC LIMIT 10";
        SachDAO sachDAO = new SachDAO(context);
        List<Top>list = new ArrayList<>();
        Cursor c = db.rawQuery(sqlTop,null);
        while (c.moveToNext()){
            Top topmodel = new Top();
            Sach sach = sachDAO.getid(c.getString(c.getColumnIndex("maSach")));
            topmodel.setTenSach(sach.getTenSach());
            topmodel.setSoLuong(Integer.parseInt(c.getString(c.getColumnIndex("soLuong"))));
            list.add(topmodel);
        }
        return list;
    }
    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay){
        String sqlDoanhThu = "SELECT SUM(tienThue) as doanhThu FROM PhieuMuon WHERE ngay BETWEEN ? AND ?";
        List<Integer>list = new ArrayList<Integer>();
        Cursor c = db.rawQuery(sqlDoanhThu,new String[]{tuNgay,denNgay});
        while (c.moveToNext()){
            try {
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhThu"))));
            }catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }
}
