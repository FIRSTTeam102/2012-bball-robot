/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author Administrator
 */
public class RemoveSlackwSensorAndArm extends CommandGroup {

    public RemoveSlackwSensorAndArm() {
        
        // NOTE: this makes sure that the cannon is in an "unarmed" position before it arms.
        addSequential(new EngageClutch(false));     // This releases the clutch.
        addSequential(new RemoveCannonSlackwithSensor());
        addSequential(new ArmCannon());
    }
}