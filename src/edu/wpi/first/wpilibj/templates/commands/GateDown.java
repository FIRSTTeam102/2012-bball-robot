/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.templates.subsystems.BallGate;


/**
 *
 * @author Administrator
 */
public class GateDown extends CommandBase {

    public GateDown() {
        // Use requires() here to declare subsystem dependencies
        requires(ballGate);
        this.setTimeout(BallGate.TIME_TO_RELEASE_GATE_DOWN);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        ballGate.gateDown();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
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