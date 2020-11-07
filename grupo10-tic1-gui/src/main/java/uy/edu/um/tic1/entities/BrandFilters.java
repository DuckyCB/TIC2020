package uy.edu.um.tic1.entities;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class BrandFilters {


    private Integer id;
    private String name;


    @Override
    public String toString(){

        String query = "?";

        if (id != null)
            query += "id=" + id.toString() + "&";

        if (name != null)
            query += "name=" + name + "&";

        return query;
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

    public BrandFilters(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public BrandFilters() {
    }
}
