/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author MRuser
 */
public class MainAuthor {
    public static void main(String h[])
    {
    Author au=new Author();
    
    au.setAname("j.k.row");
    
    Book bk=new Book();
    bk.setBname("harry potter");
    Book bk2=new Book();
    bk2.setBname("fire of princess");
    au.getSetOfBook().add(bk);
    au.getSetOfBook().add(bk2);
    bk.getSetOfAuthor().add(au);
      Configuration con=new Configuration();
      con.configure("hibernate.cfg.xml");
     SessionFactory fac= con.buildSessionFactory();
     Session sess=fac.openSession();
     Transaction tx=sess.beginTransaction();
     sess.save(au);
     sess.save(bk);
     sess.save(bk2);
     tx.commit();

    }
}
