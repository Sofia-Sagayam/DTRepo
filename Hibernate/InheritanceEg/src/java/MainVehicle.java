
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
public class MainVehicle {
    public static void main(String h[])
    {
        Vehicle v1=new Vehicle();
        v1.setVid(1);
        v1.setVname("some vehicle");
        
        TwoWheeler tw=new TwoWheeler();
        tw.setVid(12);
        tw.setVname("bike");
        tw.setHandleBar("bike handle bar");
        
        FourWheeler fw=new FourWheeler();
        fw.setVid(13);
        fw.setVname("car");
        fw.setSteeringWheel("car's streeing wheel");
        
        Configuration conf=new Configuration();
        conf.configure("hibernate.cfg.xml");
        SessionFactory factory=conf.buildSessionFactory();
        Session sess=factory.openSession();
        Transaction trx=sess.beginTransaction();
        sess.save(v1);
        sess.save(tw);
        sess.save(fw);
        
        trx.commit();
        sess.close();
        
             
    }
}
