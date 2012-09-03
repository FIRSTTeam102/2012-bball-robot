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
public class EngageClutch extends CommandBase {

    boolean engage;
    public EngageClutch(boolean engg) {
        requires(cannon);
        engage = engg;
        
        // The clutch has to be release for some time to allow unwind.
        if(!engage)
            this.setTimeout(Cannon.TIME_TO_RELEASE_CLUTCH);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(engage)
            cannon.engageClutch();
        else
            cannon.disengageClutch();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(engage)
            return true;
        else
            return this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}