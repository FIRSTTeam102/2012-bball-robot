/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;


/**
 *
 * @author Administrator
 */
public class ArmWithTrigger extends CommandBase {

    public ArmWithTrigger() {
        requires(kicker);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        kicker.engageClutch();
        kicker.zeroEncoder();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        kicker.armWithTrigger(oi.getXBox());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}