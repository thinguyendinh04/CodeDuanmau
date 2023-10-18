package com.example.ph34050_pnlibary.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.ph34050_pnlibary.DAO.ThongKeDAO;
import com.example.ph34050_pnlibary.Model.Top;
import com.example.ph34050_pnlibary.R;
import com.example.ph34050_pnlibary.adapter.TopAdapter;

import java.util.ArrayList;

public class TopFragment extends Fragment {
ListView lv;
ArrayList<Top>list;
TopAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_top, container, false);
        lv = v.findViewById(R.id.lvTop);
        ThongKeDAO thongKeDAO = new ThongKeDAO(getActivity());
        list = (ArrayList<Top>) thongKeDAO.getTop();
        adapter = new TopAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
        return v;
    }
}