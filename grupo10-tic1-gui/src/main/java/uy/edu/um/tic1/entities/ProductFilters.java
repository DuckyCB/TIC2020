package uy.edu.um.tic1.entities;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class ProductFilters {

    private Integer id;
    private String name;
    private Character gender;
    private Integer brand_id;
    private String size;
    private String color;
    private Integer stock;
    private Double from;
    private Double to;
    private String type;
    private Integer subtype;
    private Boolean hasStock;
    private Integer order;


    @Override
    public String toString(){

        String query = "?";

        if (id != null)
            query += "id=" + id.toString() + "&";

        if (name != null)
            query += "name=" + name + "&";

        if (gender != null)
            query += "gender=" + gender.toString() + "&";

        if (brand_id != null)
            query += "brand=" + brand_id.toString() + "&";

        if (size != null)
            query += "size=" + size + "&";

        if (color != null)
            query += "color=" + color + "&";

        if (stock != null)
            query += "stock=" + stock.toString() + "&";

        if (from != null)
            query += "from=" + from.toString() + "&";

        if (to != null)
            query += "to=" + to.toString() + "&";

        if (type != null)
            query += "type=" + type + "&";

        if (subtype != null)
            query += "subtype=" + subtype + "&";


        if (hasStock != null)
            query += "hasStock=" + hasStock.toString() + "&";

        if (order != null)
            query += "order=" + order.toString() + "&";
        return query;
    }


    public Boolean filtering(){

        if (id!=null || name!=null || gender!=null || brand_id!=null || size!=null || color!=null ||
                stock!=null || from!=null || to!=null || type!=null || hasStock!= null || order != null || subtype != null){
            return true;
        }
        else {
            return false;
        }
    }


    public ProductFilters() {
    }

    public ProductFilters(Integer id, String name, Character gender, Integer brand_id, String size, String color, Integer stock, Double from, Double to, String type, Integer subtype, Boolean hasStock, Integer order) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.brand_id = brand_id;
        this.size = size;
        this.color = color;
        this.stock = stock;
        this.from = from;
        this.to = to;
        this.type = type;
        this.subtype = subtype;
        this.hasStock = hasStock;
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public Integer getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Integer brand_id) {
        this.brand_id = brand_id;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getFrom() {
        return from;
    }

    public void setFrom(Double from) {
        this.from = from;
    }

    public Double getTo() {
        return to;
    }

    public void setTo(Double to) {
        this.to = to;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getHasStock() {
        return hasStock;
    }

    public void setHasStock(Boolean hasStock) {
        this.hasStock = hasStock;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getSubtype() {
        return subtype;
    }

    public void setSubtype(Integer subtype) {
        this.subtype = subtype;
    }
}
