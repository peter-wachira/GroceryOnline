/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Chonzom
 */
@Entity
@Table(name="Customer")
public class Customer {
    
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
    
 
    @OneToMany(mappedBy="customer")
    private List<Orders> orders;
    
    @Column(name="name")
    private String name; 
    
    @Column(name="dob")
    private String dob;
    
    @Column(name="address")
    private String address;
    
    @Column(name="phoneno")
    private String phoneno;
    
    @Column(name="status")
    private String status;
    
    @Column(name="password")
    private String password;
    
    @Column(name="regdate")
    private String regdate;
    
    @Column(name="order_num")
    private int order_num;

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public int getOrder_num() {
        return order_num;
    }

    public void setOrder_num(int order_num) {
        this.order_num = order_num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }
   

//    @Override
//    public String toString() {
//        return "Customer{" + "name=" + name + ", id=" + id + ", dob=" + dob + ", address=" + address + ", phoneno=" + phoneno + ", status=" + status + ", password=" + password + ", regdate=" + regdate + '}';
//    }
//    
    
    
    
    
}
