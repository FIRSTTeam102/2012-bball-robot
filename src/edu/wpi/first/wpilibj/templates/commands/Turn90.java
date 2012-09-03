/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;


/**
 *
 * @author Administrator
 */
public class Turn90 extends CommandBase {

    public Turn90() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        driveTrain.enableGyroSetPoint(0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {

        return (Math.abs(driveTrain.gyro.getAngle()) < 1.0);
    }

    // Called once after isFinished returns true
    protected void end() {
        driveTrain.disablePIDMovement();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        driveTrain.disablePIDMovement();
    }
}