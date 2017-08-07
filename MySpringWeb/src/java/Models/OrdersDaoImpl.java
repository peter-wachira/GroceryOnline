/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.List;

import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Chonzom
 */

@Repository
public class OrdersDaoImpl extends HibernateDaoSupport implements OrdersDao{
    
    @Autowired
    private SessionFactory sessionFactory;

     @Override
     @Transactional
    public Long addOrder(Orders order) {
        return (Long)this.sessionFactory.getCurrentSession().save(order);
        }

    
    @Override
    @Transactional
    public Orders getOrder(long id) {
        return (Orders) this.sessionFactory.getCurrentSession().get(Orders.class,id);
    }
    
    
    @Override
    @Transactional
    public List<Orders> listOrders() {
     Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(Orders.class);
     return (List)criteria.list();
     
    }

    @Override
    @Transactional
    public void changeStatus(Orders order) {
     this.sessionFactory.getCurrentSession().update(order);
    }
    
    

    @Override
    @Transactional
    public void update(Orders order) {
    
        this.sessionFactory.getCurrentSession().update(order);
    }
    
    
    @Override
    @Transactional
    public List<Orders> getCustorder(long id){
    Query query= this.sessionFactory.getCurrentSession().createQuery("from Orders where cust_id:c ");
        query.setLong("c",id);
        return query.list();
    
    }

    @Override
    @Transactional
    public List<Orders> newOrders() {
        Query query=this.sessionFactory.getCurrentSession().createQuery("from Orders where status=:s");
            query.setString("s","shipped");   
        return query.list();
    }

}