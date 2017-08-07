/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.List;

/**
 *
 * @author Chonzom
 */
public interface CategoryDao {
    
    Category getCategory(long id);
    List<Category> listCategory();
    void changeStatus(Category category);
    void update(Category category);
    void addCategory(Category category);
    
}
