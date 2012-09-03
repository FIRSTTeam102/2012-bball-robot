/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;


/**
 *
 * @author Administrator
 */
public class DriveStraight extends CommandBase {

    double angle = 0.0;
    public DriveStraight(double angle, double driveTime) {
        requires(driveTrain);
        this.angle = angle;
        this.setTimeout(driveTime);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        driveTrain.enableGyroSetPoint(angle);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        driveTrain.drive(1.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.isTimedOut();
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