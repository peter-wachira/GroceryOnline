/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.*/
 
package Models;
import java.util.List;

/**
 *
 * @author Chonzom
 */
public interface CustomerDao {
    Customer getCustomer(long id);
    List <Customer> listCustomer();
    void changeStatus(Customer customer);
    void update(Customer customer);
    void addCustomer(Customer customer);
    List<Customer> newCustomer();
    
    
    
}
