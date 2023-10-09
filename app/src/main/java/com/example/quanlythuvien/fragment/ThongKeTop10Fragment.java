package com.example.quanlythuvien.fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.quanlythuvien.R;
import com.example.quanlythuvien.adapter.Top10Adapter;
import com.example.quanlythuvien.dao.ThongKeDAO;
import com.example.quanlythuvien.model.Sach;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ThongKeTop10Fragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thong_ke_top10, container, false);

        RecyclerView recyclerTop10 = view.findViewById(R.id.recycleTop10);

        ThongKeDAO thongKeDAO = new ThongKeDAO(getContext());
        ArrayList<Sach> list = thongKeDAO.getTop10();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerTop10.setLayoutManager(linearLayoutManager);
        Top10Adapter adapter = new Top10Adapter(getContext(), list);
        recyclerTop10.setAdapter(adapter);
        return view;
    }
}