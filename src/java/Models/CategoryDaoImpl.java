/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Chonzom
 */

@Repository
public class CategoryDaoImpl extends HibernateDaoSupport implements CategoryDao {
    
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public Category getCategory(long id) {
        return (Category)this.sessionFactory.getCurrentSession().get(Category.class, id);
    }

    @Override
    @Transactional
    public List<Category> listCategory() {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(Category.class);
        return (List<Category>) criteria.list();
    }

    @Override
    @Transactional
    public void changeStatus(Category category) {
         Category c=getCategory(category.getId());
       
         if(c.getStatus().equals("active"))
        {
        c.setStatus("inactive");
        this.sessionFactory.getCurrentSession().update(c);
        }
        else if(c.getStatus().equals("inactive"))
        {
                    c.setStatus("active");
                    this.sessionFactory.getCurrentSession().update(c);
        }

    }

    @Override
    @Transactional
    public void update(Category category) {
        this.sessionFactory.getCurrentSession().update(category);
    }

    @Override
    @Transactional
    public void addCategory(Category category) {
        this.sessionFactory.getCurrentSession().save(category);
                
    }
    
    
    
    
    
    
}
