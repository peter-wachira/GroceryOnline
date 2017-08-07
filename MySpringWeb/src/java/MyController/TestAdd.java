/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MyController;

import Models.Category;
import Models.CategoryDao;
import Models.Customer;
import Models.CustomerDao;
import Models.Orders;
import Models.OrdersDao;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Chonzom
 */
public class TestAdd {
    
    public static void main(String args[]){
        
        
        ApplicationContext ctx=new ClassPathXmlApplicationContext("file:web/WEB-INF/applicationContext.xml");
//        CustomerDao cdao = (CustomerDao) ctx.getBean("customerDaoImpl");

           OrdersDao odao=(OrdersDao)ctx.getBean("ordersDaoImpl");
           Orders ord=new Orders();
           ord.setDate("13/12/2016");
           ord.setNo_of_items(3);
           ord.setStatus("pending");
           ord.setTotal_payment(450);
           
           ord.setStat_date("13/12/2016");
           List<Long> itemlist=new ArrayList();
           itemlist.add((long)10);
           itemlist.add((long)1);
           itemlist.add((long)6);
           System.out.println(itemlist.toString());
           ord.setItemlist(itemlist.toString());
           
           
            Customer cust= new Customer();
            CustomerDao cdao=(CustomerDao)ctx.getBean("customerDaoImpl");
            cust=cdao.getCustomer(3);
            ord.setCustomer(cust);
            odao.addOrder(ord);
           


//        OrdersDao odao=(OrdersDao) ctx.getBean("ordersDaoImpl");
//        
//        Customer customer = new Customer();
//        
//        customer.setName("aman");
//        customer.setAddress("dehradun");
//        customer.setDob("10/01/1995");
//        
//        customer.setPassword("drish");
//        customer.setPhoneno("9536468032");
//        customer.setRegdate("01/11/2016");
//        customer.setStatus("active");
//        cdao.addCustomer(customer);
        


//        

//        CategoryDao cdao=(CategoryDao)ctx.getBean("categoryDaoImpl");
//        Category category1=new Category();
//        category1.setId((long)1);
//        category1.setImage("images/dal.jpg");
//        category1.setName("Dal and Pulses");
//        category1.setStatus("in stock");
//        cdao.addCategory(category1);
    }
}
