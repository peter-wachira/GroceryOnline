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
public interface OrdersDao {
    
    Orders getOrder(long id);
    List<Orders> listOrders();
    void changeStatus(Orders order);
    void update(Orders order);
     
    Long addOrder(Orders orders);
    List<Orders> getCustorder(long id);
    
    List<Orders> newOrders();
    
    
    
    
    
    
}
