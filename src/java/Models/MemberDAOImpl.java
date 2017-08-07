/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class MemberDAOImpl extends HibernateDaoSupport implements MemberDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void createMember(Members mem) {
        this.sessionFactory.getCurrentSession().save(mem);
    }

    @Override
    @Transactional
    public Members getMember(int id) {
        return (Members)this.sessionFactory.getCurrentSession().get(Members.class, id);
    }

    @Override
    @Transactional
    public List<Members> listMembers() {
        
        System.out.println("current session : "+sessionFactory);
        Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Members.class);
        return (List<Members>) criteria.list();
    }

    @Override
    @Transactional
    public void changeStatus(Members mem) {
        Members m=getMember(mem.getId());
        if(m.getStatus().equals("Main Admin"))
        {}
        else if(m.getStatus().equals("active"))
        {
        m.setStatus("inactive");
        this.sessionFactory.getCurrentSession().update(m);
        }
        else if(m.getStatus().equals("inactive"))
        {
                    m.setStatus("active");
                    this.sessionFactory.getCurrentSession().update(m);
        }
    }

    @Override
    @Transactional
    public void update(Members mem) {
       this.sessionFactory.getCurrentSession().update(mem);
    }
    
}
