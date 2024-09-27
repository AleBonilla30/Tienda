package model;

import java.util.List;

public class Producto {
    private int id;
    private String availabilityStatus;
    private String category;
    private String description;
    private Double dimensionsDepth;
    private Double dimensionsHeight;
    private Double dimensionsWidth;
    private Double discountPercentage;
    private List<String> images;
    private Double price;
    private Double rating;
    private String shippingInformation;
    private String sku;
    private int stock;
    private String thumbnail;
    private String title;
    private String warrantyInformation;
    private Double weight;

    public Producto() {
    }

    public Producto( String availabilityStatus, String category, String description, Double dimensionsDepth, Double dimensionsHeight, Double dimensionsWidth, Double discountPercentage, List<String> images, Double price, Double rating, String shippingInformation, String sku, int stock, String thumbnail, String title, String warrantyInformation, Double weight) {
        this.availabilityStatus = availabilityStatus;
        this.category = category;
        this.description = description;
        this.dimensionsDepth = dimensionsDepth;
        this.dimensionsHeight = dimensionsHeight;
        this.dimensionsWidth = dimensionsWidth;
        this.discountPercentage = discountPercentage;
        this.images = images;
        this.price = price;
        this.rating = rating;
        this.shippingInformation = shippingInformation;
        this.sku = sku;
        this.stock = stock;
        this.thumbnail = thumbnail;
        this.title = title;
        this.warrantyInformation = warrantyInformation;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getDimensionsDepth() {
        return dimensionsDepth;
    }

    public void setDimensionsDepth(Double dimensionsDepth) {
        this.dimensionsDepth = dimensionsDepth;
    }

    public Double getDimensionsHeight() {
        return dimensionsHeight;
    }

    public void setDimensionsHeight(Double dimensionsHeight) {
        this.dimensionsHeight = dimensionsHeight;
    }

    public Double getDimensionsWidth() {
        return dimensionsWidth;
    }

    public void setDimensionsWidth(Double dimensionsWidth) {
        this.dimensionsWidth = dimensionsWidth;
    }

    public Double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getShippingInformation() {
        return shippingInformation;
    }

    public void setShippingInformation(String shippingInformation) {
        this.shippingInformation = shippingInformation;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWarrantyInformation() {
        return warrantyInformation;
    }

    public void setWarrantyInformation(String warrantyInformation) {
        this.warrantyInformation = warrantyInformation;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
