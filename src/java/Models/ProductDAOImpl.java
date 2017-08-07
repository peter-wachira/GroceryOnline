package Models;


import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

public class ProductDAOImpl extends HibernateDaoSupport implements ProductDAO 
{
    @Autowired
    private SessionFactory sessionFactory;
    
    @Transactional
    @Override
    public List<Product> listProducts() {
        Criteria criteria=this.sessionFactory.getCurrentSession().createCriteria(Product.class);
        return (List<Product>) criteria.list();
    }
    
        
    @Transactional
    @Override
    public Product getProduct(long id) {
    return (Product)this.sessionFactory.getCurrentSession().get(Product.class, id);
    }

    @Transactional
    @Override
    public void addProduct(Product prod) {
    this.sessionFactory.getCurrentSession().save(prod);
    }

    @Transactional
    @Override
    public void changeStatus(Product prod) {
        long cid=prod.getPid();
        Product c=getProduct(cid);
        if(c.getStatus().equals("Active"))
        {
        c.setStatus("inactive");
        this.sessionFactory.getCurrentSession().update(c);
        }
        else if(c.getStatus().equals("inactive"))
        {
                    c.setStatus("Active");
                    this.sessionFactory.getCurrentSession().update(c);
        }
    }
    
    @Transactional
    @Override
    public void update(Product prod) {
        this.sessionFactory.getCurrentSession().update(prod);
    }

    @Override
    public List<Product> stock_over() {
        Query query= this.sessionFactory.getCurrentSession().createQuery("from Product where stock=:s");
  
//        List List = session.createCriteria(Employee.class).add( Restrictions.lt("age", new Integer(24) ) ).list(); 

        query.setInteger("s",10);
        return query.list();
        
    }
    
    
}
