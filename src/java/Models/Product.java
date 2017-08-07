package Models;


import Models.Category;
import Models.Subcategory;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity 
@Table(name="Product")
public class Product {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="pid")
    private long pid;
    
    @Column(name="pname")
    private String pname;
    
    @ManyToOne
    @JoinColumn(name="cat_id")
    private Category category;
    
    @ManyToOne
    @JoinColumn(name="id")
    private Subcategory subcategory;
    
    @Column(name="price")
    private int price;

    @Column(name="stock")
    private int stock;
    
    @Column(name="status")
    private String status;

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Subcategory getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(Subcategory subcategory) {
        this.subcategory = subcategory;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
