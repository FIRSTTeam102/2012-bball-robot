/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 * @author Administrator
 */
public class ShootTwoSimple extends CommandGroup {

    public ShootTwoSimple() {
        
        DriverStation ds = DriverStation.getInstance();
        double preShotWaitTime = ds.getAnalogIn(4); // How many seconds to wait in autonomous?
        int cannonSetPoint = 640;

        System.out.println("Pre-Wait: " + preShotWaitTime);
        addSequential(new WaitCommand("Pre-Autonomous Wait", preShotWaitTime));
        addSequential(new SetCannonSetPoint(cannonSetPoint));
        System.out.println("cannon SetPoint: " + cannonSetPoint);
        addSequential(new RemoveSlackAndArm());
        // NOTE: one ball is already in the cannon
        System.out.println("Shoot 1");
        addSequential(new Shoot());

        // Second Shot
        addSequential(new GateDownThenUp());
        addSequential(new RemoveSlackwDiffAndArm());
        System.out.println("Shoot 2");
        addSequential(new Shoot());
    }
}