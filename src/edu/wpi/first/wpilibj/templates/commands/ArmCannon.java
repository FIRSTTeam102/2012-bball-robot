/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.templates.subsystems.Cannon;


/**
 *
 * @author Administrator
 */
public class ArmCannon extends CommandBase {

    int encoderTarget = 0;

    public ArmCannon() {
        // Use requires() here to declare subsystem dependencies
        requires(cannon);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        // make sure if this is run twice we do not overwind.
        encoderTarget = cannon.cannonSetPoint;
        if(!cannon.encoderLimitReached(encoderTarget))
        {
            cannon.turnOnWinchMotor(Cannon.WINCH_MOTOR_SPEED);
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return cannon.encoderLimitReached(encoderTarget);
    }

    // Called once after isFinished returns true
    protected void end() {
        cannon.turnOffWinchMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}