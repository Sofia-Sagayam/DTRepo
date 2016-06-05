/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mainclass;

import model.UserDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author MRuser
 */
public class MainUser {
    public static void main(String h[])
    {
       
        Configuration config=new Configuration();
        config.configure("hibernate.cfg.xml");
        SessionFactory factory=config.buildSessionFactory();
        Session session=factory.openSession();
        Transaction tx=session.beginTransaction();
       UserDetail user=(UserDetail)session.get(UserDetail.class,3);
      //  user.setUname("new user");
       session.delete(user);
        tx.commit();
        session.close();
        
       
       
    }
}
