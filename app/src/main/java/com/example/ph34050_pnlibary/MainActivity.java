package com.example.ph34050_pnlibary;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import com.example.ph34050_pnlibary.Fragment.AddUserFragment;
import com.example.ph34050_pnlibary.Fragment.ChangePassFragment;
import com.example.ph34050_pnlibary.Fragment.DoanhThuFragment;
import com.example.ph34050_pnlibary.Fragment.LoaiSachFragment;
import com.example.ph34050_pnlibary.Fragment.PhieuMuonFragment;
import com.example.ph34050_pnlibary.Fragment.SachFragment;
import com.example.ph34050_pnlibary.Fragment.ThanhVienFragment;
import com.example.ph34050_pnlibary.Fragment.TopFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    Toolbar toolbar;
    View mHeaderView;
    TextView edUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar1);
        //set toolbar thay the cho actionbar
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.baseline_menu_24);
        ab.setDisplayHomeAsUpEnabled(true);
        NavigationView nv = findViewById(R.id.nvView);
        FragmentManager manager = getSupportFragmentManager();
        setTitle("Quản lý phiếu mượn");
        //  Toast.makeText(getApplicationContext(), "Quản lý phiếu mượn", Toast.LENGTH_SHORT).show();
        PhieuMuonFragment PhieuMuonFragment = new PhieuMuonFragment();
        manager.beginTransaction()
                .replace(R.id.flContent,PhieuMuonFragment)
                .commit();
        //show user in header
        mHeaderView = nv.getHeaderView(0);
        edUser = mHeaderView.findViewById(R.id.txtUser);
        Intent i = getIntent();
        edUser.setText("Welcome !");

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();
                if (item.getItemId() == R.id.nav_PhieuMuon) {
                    setTitle("Quản lý phiếu mượn");
                  //  Toast.makeText(getApplicationContext(), "Quản lý phiếu mượn", Toast.LENGTH_SHORT).show();
                    PhieuMuonFragment PhieuMuonFragment = new PhieuMuonFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent,PhieuMuonFragment)
                            .commit();  // Hiển thị Fragment Phiếu Mượn mặc định khi mở ứng dụng
                } else if (item.getItemId() == R.id.nav_LoaiSach) {
                    setTitle("Quản lý loại sách");
                    Toast.makeText(getApplicationContext(), "Quản lý loại sách", Toast.LENGTH_SHORT).show();
                    LoaiSachFragment loaiSachFragment = new LoaiSachFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent,loaiSachFragment)
                            .commit();
                } else if (item.getItemId() == R.id.nav_Sach) {
                    setTitle("Quản lý sách");
                    Toast.makeText(getApplicationContext(), "Quản lý sách", Toast.LENGTH_SHORT).show();
                    SachFragment sachFragment = new SachFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent,sachFragment)
                            .commit();
                } else if (item.getItemId() == R.id.sub_Top) {
                    setTitle("Top 10 sách mượn nhiều nhất");
                    Toast.makeText(getApplicationContext(), "Top 10 sách mượn nhiều nhất", Toast.LENGTH_SHORT).show();
                    TopFragment topFragment = new TopFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent,topFragment)
                            .commit();

                } else if (item.getItemId() == R.id.sub_Doanhthu) {
                    setTitle("Thống kê doanh thu");
                    Toast.makeText(getApplicationContext(), "Thống kê doanh thu", Toast.LENGTH_SHORT).show();
                    DoanhThuFragment doanhThuFragment = new DoanhThuFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent,doanhThuFragment)
                            .commit();

                } else if (item.getItemId() == R.id.sub_AddUser) {
                    setTitle("Thêm người dùng");
                    Toast.makeText(getApplicationContext(),"Thêm người dùng",Toast.LENGTH_SHORT).show();
                    AddUserFragment addUserFragment = new AddUserFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent,addUserFragment)
                            .commit();

                } else if (item.getItemId() == R.id.sub_Pass) {
                    setTitle("Đổi mật khẩu");
                    Toast.makeText(getApplicationContext(),"Đổi mật khẩu",Toast.LENGTH_SHORT).show();
                    ChangePassFragment changePassFragment = new ChangePassFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent,changePassFragment)
                            .commit();

                } else if (item.getItemId() == R.id.nav_ThanhVien) {
                    setTitle("Quản lý thành viên");
                    Toast.makeText(getApplicationContext(),"Quản lý thành viên",Toast.LENGTH_SHORT).show();
                    ThanhVienFragment thanhVienFragment = new ThanhVienFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent,thanhVienFragment)
                            .commit();
                } else if (item.getItemId() == R.id.sub_Logout) {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
                drawer.closeDrawers();
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home)
            drawer.openDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }
}