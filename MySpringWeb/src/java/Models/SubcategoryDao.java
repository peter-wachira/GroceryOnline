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
public interface SubcategoryDao {
    
    Subcategory getSubcategory(long id);
    List<Subcategory> listSubcategory();
    void changeStatus(Subcategory subcategory);
    void update(Subcategory subcategory);
    void addSubcategory(Subcategory subcategory);
    long getCategoryId(long subid);
    
    
}
