/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.templates.RobotMap;

/**
 *
 * @author Administrator
 */
public class ShootTwo extends CommandGroup {

    public ShootTwo() {
        DriverStation ds = DriverStation.getInstance();

        boolean center = ds.getDigitalIn(RobotMap.autonomousCenter);    // Are we in the center position?
        boolean left = ds.getDigitalIn(RobotMap.autonomousLeft);      // if not in the center are we on the left?
        boolean gotoRamp = ds.getDigitalIn(RobotMap.autonomousRamp);// Should we go to the ramp after shooting?
        double preShotWaitTime = ds.getAnalogIn(4) * 2.0; // How many seconds to wait in autonomous?
        int cannonSetPoint;
        double driveAngleToRamp;
        double driveTime;

        // NOTE: The gyro has already been cleared.
        // NOTE: the compressor should come on when Autonomous is enabled.
        addSequential(new WaitCommand("Pre-Autonomous Wait", preShotWaitTime));

        if(center)
            cannonSetPoint = 300;
        else if(left)
        {
            addSequential(new TurnToAngle(7.0));
            cannonSetPoint = 350;
        }
        else
        {
            addSequential(new TurnToAngle(-7.0));
            cannonSetPoint = 350;   // Right side of the key.
        }

        addSequential(new SetCannonSetPoint(cannonSetPoint));
        addSequential(new RemoveSlackAndArm());
        // NOTE: one ball is already in the cannon
        addSequential(new Shoot());

        // The second shot.
        addSequential(new RemoveSlackAndArm());
        addSequential(new GateDownThenUp());
        addSequential(new Shoot());

        // See if we should move to the ramp.
        if(gotoRamp)
        {
            if(center)
            {
                driveAngleToRamp = 90.0;
                driveTime = 10.0;
            }
            else if(left)
            {
                driveAngleToRamp = 85.0;
                driveTime = 12.0;
            }
            else
            {
                driveAngleToRamp = -85.0;
                driveTime = 12.0;
            }
            addSequential(new TurnToAngle(driveAngleToRamp));
            addSequential(new DriveStraight(driveAngleToRamp, driveTime));
            
            addParallel(new RunConveyorTimed(2.0));
            addSequential(new TilterArmDown());
        }
    }
}