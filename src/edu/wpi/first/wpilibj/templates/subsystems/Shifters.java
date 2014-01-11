/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import Team102Lib.MessageLogger;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author Administrator
 */
public class Shifters extends Subsystem {

    Solenoid shiftSolenoidLow;
    Solenoid shiftSolenoidHigh;

    public Shifters()
    {
        shiftSolenoidLow = new Solenoid(RobotMap.solenoidModule, RobotMap.shiftSolenoidHigh);
        shiftSolenoidHigh = new Solenoid(RobotMap.solenoidModule, RobotMap.shiftSolenoidLow);
        shiftLow();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void updateStatus()
    {
        SmartDashboard.putBoolean("shiftSolenoidHigh: ", shiftSolenoidLow.get());
        SmartDashboard.putBoolean("shiftSolenoidLow: ", shiftSolenoidHigh.get());
    }
    public void shiftHigh()
    {
        shiftSolenoidLow.set(false);
        shiftSolenoidHigh.set(true);
        shiftSolenoidLow.get();
    }
    
     public void shiftToggle()
    {
        boolean shiftlow = shiftSolenoidLow.get();
        boolean  shifthigh = shiftSolenoidHigh.get();
        MessageLogger.LogMessage("Shift Low\t" + shiftlow + "Shift High\t" + shifthigh);
        if((!shiftSolenoidLow.get()) && (shiftSolenoidHigh.get()))
            shiftLow();
        else
            shiftHigh();
    }
    public void shiftLow()
    {
        shiftSolenoidLow.set(true);
        shiftSolenoidHigh.set(false);
    }

}