/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.util.Comparator;
import com.dotPycsLib.Modelclasses.*;
/**
 *
 * @author Corinna
 */
public class GuideComparer implements Comparator<Guide>
{
    @Override
    public int compare(Guide o1, Guide o2) 
    {
        return o1.getLast_name().compareTo(o2.getLast_name());
    }
}
