
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MRuser
 */
public class MainClass {
    public static void main(String h[])
    {
        
          
      Configuration con=new Configuration();
      con.configure("hibernate.cfg.xml");
     SessionFactory fac= con.buildSessionFactory();
     Session sess=fac.openSession();
     Transaction tx=sess.beginTransaction();
    Query qu= sess.createQuery("FROM Data");
    Data d;
    List l=qu.list();
    for(Object o:l)
    {
     d=   (Data)o;
     System.out.println(d.getDid()+" "+d.getDname());
    }
     
     tx.commit();
     sess.close();
        
    }
}
