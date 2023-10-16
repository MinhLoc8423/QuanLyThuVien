package com.example.quanlythuvien.fragment;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quanlythuvien.R;
import com.example.quanlythuvien.adapter.SachAdapter;
import com.example.quanlythuvien.dao.LoaiSachDAO;
import com.example.quanlythuvien.dao.SachDAO;
import com.example.quanlythuvien.model.LoaiSach;
import com.example.quanlythuvien.model.Sach;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class QLSachFragment extends Fragment {
    private SachDAO  sachDAO;
    private RecyclerView recyclerSach;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_q_l_sach, container, false);

        recyclerSach = view.findViewById(R.id.recyclerSach);
        FloatingActionButton floatAdd = view.findViewById(R.id.floatAdd);

        sachDAO = new SachDAO(getContext());
        loadData();
        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        return view;
    }

    private void loadData(){
        ArrayList<Sach> list = sachDAO.getDSSach();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerSach.setLayoutManager(linearLayoutManager);
        SachAdapter sachAdapter = new SachAdapter(getContext(), list, getDSLoaiSach(), sachDAO);
        recyclerSach.setAdapter(sachAdapter);
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_them_sach, null);
        builder.setView(view);

        EditText editTenSach = view.findViewById(R.id.edtTenSach);
        EditText edtTien = view.findViewById(R.id.edtTien);
        Spinner spinner = view.findViewById(R.id.spinnerLoaiSach);

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                getContext(),
                getDSLoaiSach(),
                android.R.layout.simple_list_item_1,
                new String[]{"hoten"},
                new int[]{android.R.id.text1});
        spinner.setAdapter(simpleAdapter);

        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tenSach = editTenSach.getText().toString();
                int tien = Integer.parseInt(edtTien.getText().toString());
                HashMap<String, Object> hs = (HashMap<String, Object>) spinner.getSelectedItem();
                int maLaoi = (int) hs.get("maloai");
                boolean check = sachDAO.themSachMoi(tenSach, tien, maLaoi);
                if (check) {
                    Toast.makeText(getContext(), "Thêm sách thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                }
                else {
                    Toast.makeText(getContext(), "Thêm sách không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }
    private  ArrayList<HashMap<String, Object>> getDSLoaiSach(){
        LoaiSachDAO loaiSachDAO = new LoaiSachDAO(getContext());
        ArrayList<LoaiSach> list = loaiSachDAO.getDSLoaiSach();
        ArrayList<HashMap<String, Object>> listHM = new ArrayList<>();

        for (LoaiSach loaiSach: list){
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("maloai", loaiSach.getId());
            hs.put("hoten", loaiSach.getTenLoai());
            listHM.add(hs);
        }
        return listHM;
    }
}