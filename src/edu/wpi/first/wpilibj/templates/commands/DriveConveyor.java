/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;



/**
 *
 * @author Administrator
 */


public class DriveConveyor extends CommandBase {

    static final double NULL_CONVEYOR_SPEED = -10.0;
    double conveyorSpeed;
    public DriveConveyor() {
        requires(conveyor); // reserve the chassis subsystem
        conveyorSpeed = NULL_CONVEYOR_SPEED;
    }
    public DriveConveyor(double speed) {
        requires(conveyor); // reserve the chassis subsystem
        conveyorSpeed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(conveyorSpeed == NULL_CONVEYOR_SPEED)
            conveyor.driveConveyor(oi.getXBox());
        else
            conveyor.driveConveyor(conveyorSpeed);

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

