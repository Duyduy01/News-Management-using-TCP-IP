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

import java.io.Serializable;


public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int MaLoai;
    private String TenLoai;
    private String MoTa;

    public Category() {
    }

    public Category(int MaLoai, String TenLoai, String MoTa) {
        this.MaLoai = MaLoai;
        this.TenLoai = TenLoai;
        this.MoTa = MoTa;
    }

    @Override
    public String toString() {
        return "Category{" + "MaLoai=" + MaLoai + ", TenLoai=" + TenLoai + ", MoTa=" + MoTa + '}';
    }

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int MaLoai) {
        this.MaLoai = MaLoai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String TenLoai) {
        this.TenLoai = TenLoai;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String MoTa) {
        this.MoTa = MoTa;
    }
}
