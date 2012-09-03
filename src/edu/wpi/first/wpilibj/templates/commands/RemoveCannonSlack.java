/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import Team102Lib.MovingAverage;
import edu.wpi.first.wpilibj.templates.subsystems.Cannon;


/**
 *
 * @author Administrator
 */

// NOTE: this uses a moving average of the difference in encoder value to determine when the slack has been removed.
// We keep track of the difference between encoder values each time the command is executed.  When the moving average of the
// difference in the encoder value is small enough, we know we have removed the slack.
// We also have a timeout in case the calculation is broken for some reason.
public class RemoveCannonSlack extends CommandBase {

    MovingAverage encoderDifference;
    int previousEncoderValue;
    int samplesToIgnore;

    public RemoveCannonSlack() {
        requires(cannon);
        this.setTimeout(Cannon.TIME_TO_REMOVE_SLACK);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        encoderDifference = new MovingAverage(0.0);
        samplesToIgnore = 4;
        previousEncoderValue = 0;
        System.out.println("Removing Slack");
        cannon.engageClutch();
        cannon.zeroEncoder();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        cannon.turnOnWinchMotor(Cannon.WINCH_MOTOR_SLACK_SPEED);
        int encoderValue = cannon.encoderWinch.get();
        samplesToIgnore--;
        encoderDifference.smoothValue((double) (encoderValue - previousEncoderValue));
        System.out.println("encoderValue: " + encoderValue
                + ", previousEncoderValue: " + previousEncoderValue);
        System.out.println("encoder diff: " + (encoderValue - previousEncoderValue)
                + ", currentAverage: " + encoderDifference.currentAverage());
        previousEncoderValue = encoderValue;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (this.isTimedOut() || ((encoderDifference.currentAverage() < 1.0) && (samplesToIgnore < 0)));
//        return this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
        cannon.turnOffWinchMotor();
        cannon.zeroEncoder();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}