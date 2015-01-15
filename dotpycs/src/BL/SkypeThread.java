/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BL;

/**
 *
 * @author sauphc11
 */

import com.dotPycsLib.Skypeadapter;
import com.skype.SkypeException;

public class SkypeThread implements Runnable{

    private String user="";
    
    public SkypeThread(String str)
    {
        user = str;
    }
    
    @Override
    public void run() {
        try {
            Skypeadapter.call(user);
            Thread.sleep(5000);
        } catch (SkypeException | InterruptedException ex) {
            
        }
    }
    
}
