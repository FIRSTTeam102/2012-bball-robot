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
public class RemoveCannonSlack extends CommandBase {

    public RemoveCannonSlack() {
        requires(cannon);
        this.setTimeout(Cannon.TIME_TO_REMOVE_SLACK);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        cannon.engageClutch();
        cannon.zeroEncoder();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        cannon.turnOnWinchMotor(Cannon.WINCH_MOTOR_SLACK_SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
        cannon.zeroEncoder();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        cannon.zeroEncoder();
    }
}