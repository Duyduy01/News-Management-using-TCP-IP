/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */

public class News implements Serializable {
    private static final long serialVersionUID = 1L;

    private int MaTinTuc;
    private String TieuDe;
    private String ChiTiet;
    private String LinkHinhAnh;
    private int MaLoaiTin;
    private String NgayDang;

    public News() {
    }

    public News(int MaTinTuc, String TieuDe, String ChiTiet, String LinkHinhAnh, int MaLoaiTin, String NgayDang) {
        this.MaTinTuc = MaTinTuc;
        this.TieuDe = TieuDe;
        this.ChiTiet = ChiTiet;
        this.LinkHinhAnh = LinkHinhAnh;
        this.MaLoaiTin = MaLoaiTin;
        this.NgayDang = NgayDang;
        
    }

    @Override
    public String toString() {
        return "News{" + "MaTinTuc=" + MaTinTuc + ", TieuDe=" + TieuDe + ", ChiTiet=" + ChiTiet + ", LinkHinhAnh=" + LinkHinhAnh + ", MaLoaiTin=" + MaLoaiTin + ", NgayDang=" + NgayDang + '}';
    }

    public int getMaTinTuc() {
        return MaTinTuc;
    }

    public void setMaTinTuc(int MaTinTuc) {
        this.MaTinTuc = MaTinTuc;
    }

    public String getTieuDe() {
        return TieuDe;
    }

    public void setTieuDe(String TieuDe) {
        this.TieuDe = TieuDe;
    }

    public String getChiTiet() {
        return ChiTiet;
    }

    public void setChiTiet(String ChiTiet) {
        this.ChiTiet = ChiTiet;
    }

    public String getLinkHinhAnh() {
        return LinkHinhAnh;
    }

    public void setLinkHinhAnh(String LinkHinhAnh) {
        this.LinkHinhAnh = LinkHinhAnh;
    }

    public int getMaLoaiTin() {
        return MaLoaiTin;
    }

    public void setMaLoaiTin(int MaLoaiTin) {
        this.MaLoaiTin = MaLoaiTin;
    }

    public String getNgayDang() {
        return NgayDang;
    }

    public void setNgayDang(String NgayDang) {
        this.NgayDang = NgayDang;
    }
}

