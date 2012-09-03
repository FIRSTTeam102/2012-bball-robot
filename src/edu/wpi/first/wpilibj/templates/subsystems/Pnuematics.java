/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author pingas Administrator
 */
public class Pnuematics extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    Relay compressorSwitch;

    public Pnuematics(){
        compressorSwitch = new Relay(RobotMap.compressorSwitchChannel, Relay.Direction.kForward) ;
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void switchCompressor(boolean switchOn){
        if (switchOn)
            compressorSwitch.set(Relay.Value.kOn);
        else
            compressorSwitch.set(Relay.Value.kOff);
    }
    public void updateStatus()
    {
    }
}