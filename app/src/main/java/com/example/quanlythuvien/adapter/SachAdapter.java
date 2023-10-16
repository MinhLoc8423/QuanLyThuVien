package com.example.quanlythuvien.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlythuvien.R;
import com.example.quanlythuvien.dao.SachDAO;
import com.example.quanlythuvien.model.Sach;

import java.util.ArrayList;
import java.util.HashMap;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHolder>{
    Context context;
    ArrayList<Sach> list;
    private SachDAO sachDAO;
    private ArrayList<HashMap<String, Object>> listHM;

    public SachAdapter(Context context, ArrayList<Sach> list, ArrayList<HashMap<String, Object>> listHM, SachDAO sachDAO) {
        this.context = context;
        this.list = list;
        this.listHM = listHM;
        this.sachDAO = sachDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_recyler_sach, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtMaSach.setText("Mã sách: "+list.get(position).getMaSach());
        holder.txtTenSach.setText("Tên sách: "+list.get(position).getTenSach());
        holder.txtGiaThue.setText("Giá thuê: "+list.get(position).getGiaThue());
        holder.txtMaLoai.setText("Mã loại: "+list.get(position).getMaLoai());
        holder.txtTenLoai.setText("Tên loại: "+list.get(position).getTenLoai());
        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(list.get(holder.getAdapterPosition()));
            }
        });
        holder.ivDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int check = sachDAO.xoaSach(list.get(holder.getAdapterPosition()).getMaSach());
                switch (check){
                    case 1:
                        Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                        break;
                    case 0:
                        Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                        break;
                    case -1:
                        Toast.makeText(context, "Không xóa được doanh sách này vì có trong phiếu mượn", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void showDialog(Sach sach) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sua_sach, null);
        builder.setView(view);

        EditText edtTenSach = view.findViewById(R.id.edtTenSach);
        EditText edtTien = view.findViewById(R.id.edtTien);
        TextView txtMaSach = view.findViewById(R.id.txtMaSach);
        Spinner spinner = view.findViewById(R.id.spinnerLoaiSach);

        txtMaSach.setText("Mã sách: "+sach.getMaSach());
        edtTien.setText(String.valueOf(sach.getGiaThue()));
        edtTenSach.setText(sach.getTenSach());

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                context,
                listHM,
                android.R.layout.simple_list_item_1,
                new String[]{"hoten"},
                new int[]{android.R.id.text1}
        );
        spinner.setAdapter(simpleAdapter);
        int index = 0;
        int postion = -1;
        for (HashMap<String, Object> item : listHM){
            if ((int)item.get("maloai") == sach.getMaLoai()){
                postion = index;
            }
            index++;
        }
        spinner.setSelection(postion);

        builder.setNegativeButton("Cập nhật", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String tenSach = edtTenSach.getText().toString();
                int tien = Integer.parseInt(edtTien.getText().toString());
                HashMap<String, Object> hs = (HashMap<String, Object>) spinner.getSelectedItem();
                int maLaoi = (int) hs.get("maloai");
                boolean check = sachDAO.capNhatThongTinSach(sach.getMaSach(), tenSach, tien, maLaoi);
                if (check) {
                    Toast.makeText(context, "Cập nhật sách thành công", Toast.LENGTH_SHORT).show();
                    loadData();
                }
                else {
                    Toast.makeText(context, "Cập nhật sách không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    private void loadData() {
        list.clear();
        list = sachDAO.getDSSach();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtMaSach, txtTenSach, txtGiaThue, txtMaLoai, txtTenLoai;
        ImageView ivEdit, ivDel;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMaSach = itemView.findViewById(R.id.txtMaSach);
            txtTenSach = itemView.findViewById(R.id.txtTenSach);
            txtGiaThue = itemView.findViewById(R.id.txtGiaThue);
            txtMaLoai = itemView.findViewById(R.id.txtMaLoai);
            txtTenLoai = itemView.findViewById(R.id.txtTenLoai);
            ivEdit = itemView.findViewById(R.id.ivEdt);
            ivDel = itemView.findViewById(R.id.ivDel);
        }
    }
}
