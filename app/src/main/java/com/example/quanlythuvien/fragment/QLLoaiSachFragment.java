package com.example.quanlythuvien.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.quanlythuvien.R;
import com.example.quanlythuvien.adapter.LoaiSachAdapter;
import com.example.quanlythuvien.dao.LoaiSachDAO;
import com.example.quanlythuvien.model.ItemClick;
import com.example.quanlythuvien.model.LoaiSach;

import java.util.ArrayList;

public class QLLoaiSachFragment extends Fragment {
    RecyclerView recyclerLoaiSach;
    LoaiSachDAO dao;
    EditText edtNhap;
    int maLoai;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_q_l_loai_sach, container, false);

        recyclerLoaiSach = view.findViewById(R.id.recycleLoaiSach);
        edtNhap = view.findViewById(R.id.edtLoaiSach);
        Button btnSua = view.findViewById(R.id.btnSuaSach);
        Button btnThem = view.findViewById(R.id.btnThemSach);

        dao = new LoaiSachDAO(getContext());
        loadData();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenLoai = edtNhap.getText().toString();
                if (dao.themLoaiSach(tenLoai)){
                    loadData();
                }else {
                    Toast.makeText(getContext(), "Thêm loại sách không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tenLoaiSach = edtNhap.getText().toString();
                LoaiSach loaiSach = new LoaiSach(maLoai, tenLoaiSach);
                if (dao.thayDoiLoaiSach(loaiSach)){
                    loadData();
                    edtNhap.setText("");
                }else {
                    Toast.makeText(getContext(), "Thay đổi thông tin không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    private void loadData(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerLoaiSach.setLayoutManager(linearLayoutManager);
        dao = new LoaiSachDAO(getContext());
        ArrayList<LoaiSach> list = dao.getDSLoaiSach();
        LoaiSachAdapter adapter = new LoaiSachAdapter(getContext(), list, new ItemClick() {
            @Override
            public void onLoaiSachClick(LoaiSach loaiSach) {
                edtNhap.setText(loaiSach.getTenLoai());
                maLoai = loaiSach.getId();
            }
        });
        recyclerLoaiSach.setAdapter(adapter);
    }
}