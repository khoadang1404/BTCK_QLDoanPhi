package com.example.btck_qldoanphi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.List;

public class SinhVienAdapter extends BaseAdapter {

    private MainActivity context;
    private int layout;
    private List<QLTPSinhVien> qltpSinhVienList;

    public SinhVienAdapter(MainActivity context, int layout, List<QLTPSinhVien> qltpSinhVienList) {
        this.context = context;
        this.layout = layout;
        this.qltpSinhVienList = qltpSinhVienList;
    }

    @Override
    public int getCount() {
        return qltpSinhVienList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView tvMaSV, tvTenSV, tvLopSV, tvTinhTrang, tvNgayNop;
        MaterialButton btnFix, btnDelete;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.tvMaSV = (TextView) view.findViewById(R.id.tvMaSV);
            holder.tvTenSV = (TextView) view.findViewById(R.id.tvTenSV);
            holder.tvLopSV = (TextView) view.findViewById(R.id.tvLopSV);
            holder.tvTinhTrang = (TextView) view.findViewById(R.id.tvTinhTrang);
            holder.tvNgayNop = (TextView) view.findViewById(R.id.tvNgayNop);
            holder.btnFix = (MaterialButton) view.findViewById(R.id.btnFix);
            holder.btnDelete = (MaterialButton) view.findViewById(R.id.btnDelete);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        QLTPSinhVien qltpSinhVien = qltpSinhVienList.get(i);
        holder.tvMaSV.setText("Mã sinh viên: " + qltpSinhVien.getMaSV());
        holder.tvTenSV.setText(qltpSinhVien.getTenSV());
        holder.tvLopSV.setText("Lớp: " + qltpSinhVien.getLopSV());
        holder.tvTinhTrang.setText(qltpSinhVien.getTinhTrang());
        holder.tvNgayNop.setText("Ngày nộp: " + qltpSinhVien.getNgayNop());

        //bắt sự kiện xóa & sửa
        holder.btnFix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogSuaCongViec(qltpSinhVien.getMaSV(), qltpSinhVien.getTenSV(), qltpSinhVien.getLopSV(), qltpSinhVien.getTinhTrang(),qltpSinhVien.getNgayNop(), qltpSinhVien.getId());
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogXoaCongViec(qltpSinhVien.getTenSV(), qltpSinhVien.getId());
            }
        });

        return view;
    }
}
