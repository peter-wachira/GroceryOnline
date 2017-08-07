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
public class SubcategoryDaoImpl extends HibernateDaoSupport implements SubcategoryDao{

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    @Transactional
    public Subcategory getSubcategory(long id) {
        return (Subcategory)this.sessionFactory.getCurrentSession().get(Subcategory.class,id);
        
    }

    @Override
    @Transactional
    public List<Subcategory> listSubcategory() {
        Criteria criteria = this.sessionFactory.getCurrentSession().createCriteria(Subcategory.class);
        return (List<Subcategory>) criteria.list();
    }

    @Override
    @Transactional
    public void changeStatus(Subcategory subcategory) {
        this.sessionFactory.getCurrentSession().update(subcategory);
    }

    @Override
    @Transactional
    public void update(Subcategory subcategory) {
        this.sessionFactory.getCurrentSession().update(subcategory);
    }

    @Override
    @Transactional
    public void addSubcategory(Subcategory subcategory) {
        this.sessionFactory.getCurrentSession().save(subcategory);
    }
    
    @Override
    @Transactional
    public long getCategoryId(long subid) {
        Query q=this.sessionFactory.getCurrentSession().createSQLQuery("select cat_id from subcategory where id="+subid);
//        q.setLong("s", subid);
        long cat=q.getFirstResult();
        return cat;
                        
    }

    
}
