/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author sofi
 */
@Entity
public class Vehicle5 {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int vid;
    private String vname;
    @ManyToMany(mappedBy = "veh")
    private Set<Person5> setOfPerson=new HashSet<>();

    public Set<Person5> getSetOfPerson() {
        return setOfPerson;
    }

    public void setSetOfPerson(Set<Person5> setOfPerson) {
        this.setOfPerson = setOfPerson;
    }
    
    
    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }
    
}
