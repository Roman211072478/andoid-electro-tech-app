/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fiki.math.lab.mathlab.domain.model;

/**
 *
 * @author Roman
 */
public class DcGenerator  {
    
    private static DcGenerator dcGenerator = null;
    
    private DcGenerator()
    {}
    
    public static DcGenerator getInstance()
    {
        if(dcGenerator == null)
        {
            dcGenerator = new DcGenerator();
        }
        return dcGenerator;
    }
    
    public double calcLineCurrent(double power,double volts)
    {
        return power / volts;
    }
    
     public double calcShuntCurrent(double volts,double shuntResist)
    {
        return volts / shuntResist;
    }
    public double calcArmatureCurrent(double lineCurrent, double shuntCurrent)
    {        
        return lineCurrent + shuntCurrent;
    }
    public double calcGeneratorEmf(double volts,double armatureCurrent,double armatureResistance)
    {
        return volts + (armatureCurrent * armatureResistance);
    }
    
}
