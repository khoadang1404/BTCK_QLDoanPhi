package com.example.btck_qldoanphi;

public class QLTPSinhVien {
    private int Id;
    private String maSV;
    private String tenSV;
    private String lopSV;
    private String tinhTrang;
    private String ngayNop;

    public QLTPSinhVien(int id, String maSV, String tenSV, String lopSV, String tinhTrang, String ngayNop) {
        Id = id;
        this.maSV = maSV;
        this.tenSV = tenSV;
        this.lopSV = lopSV;
        this.tinhTrang = tinhTrang;
        this.ngayNop = ngayNop;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public String getLopSV() {
        return lopSV;
    }

    public void setLopSV(String lopSV) {
        this.lopSV = lopSV;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getNgayNop() {
        return ngayNop;
    }

    public void setNgayNop(String ngayNop) {
        this.ngayNop = ngayNop;
    }
}
