package com.tuandev.excelhandle.models;

public class Object1 {
    private Long orderNumber;
    private Long chrtId;
    private String sellerArticle;
    private String wBarcodeArticle;
    private String productName;
    private String packagingDimension;
    private String weight;
    private String size;
    private String color;
    private String brand;
    private String productCode;
    private String sticker;
    private String stickerRead;

    public Object1(Long orderNumber, Long chrtId, String sellerArticle, String wBarcodeArticle, String productName, String packagingDimension, String weight, String size, String color, String brand, String productCode, String sticker, String stickerRead) {
        this.orderNumber = orderNumber;
        this.chrtId = chrtId;
        this.sellerArticle = sellerArticle;
        this.wBarcodeArticle = wBarcodeArticle;
        this.productName = productName;
        this.packagingDimension = packagingDimension;
        this.weight = weight;
        this.size = size;
        this.color = color;
        this.brand = brand;
        this.productCode = productCode;
        this.sticker = sticker;
        this.stickerRead = stickerRead;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getChrtId() {
        return chrtId;
    }

    public void setChrtId(Long chrtId) {
        this.chrtId = chrtId;
    }

    public String getSellerArticle() {
        return sellerArticle;
    }

    public void setSellerArticle(String sellerArticle) {
        this.sellerArticle = sellerArticle;
    }

    public String getwBarcodeArticle() {
        return wBarcodeArticle;
    }

    public void setwBarcodeArticle(String wBarcodeArticle) {
        this.wBarcodeArticle = wBarcodeArticle;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPackagingDimension() {
        return packagingDimension;
    }

    public void setPackagingDimension(String packagingDimension) {
        this.packagingDimension = packagingDimension;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getSticker() {
        return sticker;
    }

    public void setSticker(String sticker) {
        this.sticker = sticker;
    }

    public String getStickerRead() {
        return stickerRead;
    }

    public void setStickerRead(String stickerRead) {
        this.stickerRead = stickerRead;
    }

    @Override
    public String toString() {
        return "Object1{" +
                "orderNumber=" + orderNumber +
                ", chrtId=" + chrtId +
                ", sellerArticle=" + sellerArticle +
                ", wBarcodeArticle='" + wBarcodeArticle + '\'' +
                ", productName='" + productName + '\'' +
                ", packagingDimension='" + packagingDimension + '\'' +
                ", weight='" + weight + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", brand='" + brand + '\'' +
                ", productCode='" + productCode + '\'' +
                ", sticker='" + sticker + '\'' +
                ", stickerRead='" + stickerRead + '\'' +
                '}';
    }
}
