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
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int bid;
    private String bname;
    @ManyToMany
    private Set<Author> setOfAuthor=new HashSet();

    public Set<Author> getSetOfAuthor() {
        return setOfAuthor;
    }

    public void setSetOfAuthor(Set<Author> setOfAuthor) {
        this.setOfAuthor = setOfAuthor;
    }
    
    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }
    
}
