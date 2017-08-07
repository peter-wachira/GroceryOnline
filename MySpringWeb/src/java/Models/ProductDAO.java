package Models;

import java.util.List;

public interface ProductDAO {
    List<Product> listProducts();
    public Product getProduct(long id);
    void addProduct(Product prod);
    void changeStatus(Product prod);    
    void update(Product prod);
    List<Product> stock_over();
}
