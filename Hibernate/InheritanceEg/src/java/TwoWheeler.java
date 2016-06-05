
import javax.persistence.Entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MRuser
 */
@Entity
public class TwoWheeler extends Vehicle{
    
    private String handleBar;

    public String getHandleBar() {
        return handleBar;
    }

    public void setHandleBar(String handleBar) {
        this.handleBar = handleBar;
    }
    
}
