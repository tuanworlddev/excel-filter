package com.tuandev.excelhandle.models;

public class Object2 {
    private String jobNo;
    private String brand;
    private String name;
    private String size;
    private String color;
    private String sellerCode;
    private String sticker;
    private String barcode;

    public Object2(String jobNo, String brand, String name, String size, String color, String sellerCode, String sticker, String barcode) {
        this.jobNo = jobNo;
        this.brand = brand;
        this.name = name;
        this.size = size;
        this.color = color;
        this.sellerCode = sellerCode;
        this.sticker = sticker;
        this.barcode = barcode;
    }

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSellerCode() {
        return sellerCode;
    }

    public void setSellerCode(String sellerCode) {
        this.sellerCode = sellerCode;
    }

    public String getSticker() {
        return sticker;
    }

    public void setSticker(String sticker) {
        this.sticker = sticker;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Override
    public String toString() {
        return "Object2{" +
                "jobNo=" + jobNo +
                ", brand='" + brand + '\'' +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", sellerCode='" + sellerCode + '\'' +
                ", sticker='" + sticker + '\'' +
                ", barcode=" + barcode +
                '}';
    }
}
