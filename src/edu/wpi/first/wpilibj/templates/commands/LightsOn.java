/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;


/**
 *
 * @author Administrator
 */
public class LightsOn extends CommandBase {

    public LightsOn() {
        // Use requires() here to declare subsystem dependencies
        requires(vision);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        vision.switchLights(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}