package com.bydmr.databindingjava;

public class Ogrenci {

    private String isim;
    private int no;

    public Ogrenci(String isim, int no) {
        this.isim = isim;
        this.no = no;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
}
