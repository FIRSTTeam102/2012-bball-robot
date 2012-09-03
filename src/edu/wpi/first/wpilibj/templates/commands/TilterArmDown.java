/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.templates.subsystems.TilterArm;


/**
 *
 * @author Administrator
 */
public class TilterArmDown extends CommandBase {

    public TilterArmDown() {
        // Use requires() here to declare subsystem dependencies
        requires(tilterArm);
        this.setTimeout(TilterArm.TIME_TO_MOVE_ARM_DOWN);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        tilterArm.armDown(TilterArm.SPEED_TO_MOVE_ARM_DOWN);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
        tilterArm.armStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}