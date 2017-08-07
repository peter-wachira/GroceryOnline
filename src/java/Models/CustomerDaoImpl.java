/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

/**
 *
 * @author Chonzom
 */

@Repository
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao{
    
    @Autowired
    private SessionFactory sessionFactory;  
    
//    @Override
//    public Integer addOrder(long id, int no_of_items) {
//        Query query=(Query)this.sessionFactory.getCurrentSession().createQuery("insert into Orders(no_of_items) values(3) where cust_id:c");
//        query.setLong("c",id);
//        return no_of_items;
//    }

    @Override
    @Transactional
    public Customer getCustomer(long id) {
        System.out.println(""+this.sessionFactory);
         return (Customer) this.sessionFactory.getCurrentSession().get(Customer.class, id);
    }

    @Override
    @Transactional
    public List<Customer> listCustomer() {
       Criteria criteria =this.sessionFactory.getCurrentSession().createCriteria(Customer.class);
       return (List<Customer>) criteria.list();
    }

    @Override
    @Transactional
    public void changeStatus(Customer customer) {
         this.sessionFactory.getCurrentSession().update(customer);
    }

    @Override
    @Transactional
    public void update(Customer customer) {
        this.sessionFactory.getCurrentSession().update(customer);
    }
   
    @Override
    @Transactional
    public void addCustomer(Customer customer){
        this.sessionFactory.getCurrentSession().save(customer);
    }

    @Override
    @Transactional
    public List<Customer> newCustomer() {
        Query query=this.sessionFactory.getCurrentSession().createQuery("from Customer where regdate=:r");
        Date date=new java.util.Date();
       
        String curr_date=new SimpleDateFormat("dd/MM/yyyy").format(date);
        
        System.out.println(curr_date+"our date for today");
      
        query.setString("r",curr_date);
        return query.list();
    }
    
    
}
