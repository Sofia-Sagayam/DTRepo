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
 * @author MRuser
 */
@Entity
public class Author {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int aid;
    private String aname;
    @ManyToMany
    private Set<Book> setOfBook=new HashSet<>();

    public Set<Book> getSetOfBook() {
        return setOfBook;
    }

    public void setSetOfBook(Set<Book> setOfBook) {
        this.setOfBook = setOfBook;
    }

   
    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }
    
}
