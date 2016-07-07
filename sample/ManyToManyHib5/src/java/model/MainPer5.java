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
 * @author sofi
 */
public class MainPer5 {
    public static void main(String h[])
    {
        Person5 per=new Person5();
        per.setName("first person");
        
        Vehicle5 v=new Vehicle5();
        v.setVname("bike");
        Vehicle5 v2=new Vehicle5();
        v2.setVname("car");
        per.getVeh().add(v);
        per.getVeh().add(v2);
        v.getSetOfPerson().add(per);
        v2.getSetOfPerson().add(per);
        Configuration con=new Configuration();
        con.configure("hibernate.cfg.xml");
        SessionFactory fac=con.buildSessionFactory();
        Session sec=fac.openSession();
        Transaction tx=sec.beginTransaction();
        sec.save(per);
        sec.save(v);
        sec.save(v2);
        tx.commit();
    }
}
