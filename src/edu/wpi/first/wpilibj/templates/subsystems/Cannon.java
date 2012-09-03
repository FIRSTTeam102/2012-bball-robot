/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.ArmWithTrigger;
import edu.wpi.first.wpilibj.templates.commands.SetSetPointWithTrigger;

/**
 *
 * @author Administrator
 */
public class Cannon extends Subsystem {

    public static final double WINCH_MOTOR_SPEED = 0.7;
    public static final double TIME_TO_RELEASE_CLUTCH = 0.4;
    public static final double TIME_TO_REMOVE_SLACK = 0.4;
    public static final double WINCH_MOTOR_SLACK_SPEED = 0.1;
    
    static final double MOTOR_SCALE_FACTOR = 0.7;       // UNUSED.
    static final int ENCODER_MAX_VALUE = 700;

    public int cannonSetPoint = 0;
    public Encoder encoderWinch; // = new Encoder(RobotMap.winchEncoderPortA, RobotMap.winchEncoderPortB);
    Victor winchMotor;
    Solenoid clutchSolenoidRelease;
    Solenoid clutchSolenoidHold;

    public Cannon() {
        winchMotor = new Victor(RobotMap.winchMotorPort);
        clutchSolenoidRelease = new Solenoid(RobotMap.solenoidModule, RobotMap.clutchSolenoidReleasePort);
        clutchSolenoidHold = new Solenoid(RobotMap.solenoidModule, RobotMap.clutchSolenoidHoldPort);
        encoderWinch = new Encoder(RobotMap.winchEncoderPortA, RobotMap.winchEncoderPortB);
    }

    public void engageClutch() {
        clutchSolenoidRelease.set(true);
        clutchSolenoidHold.set(false);
    }

    public void disengageClutch() {
        clutchSolenoidRelease.set(false);
        clutchSolenoidHold.set(true);
        zeroEncoder();                      // Auto zero the encoder after releasing to prevent overwinding.
    }

    public void zeroEncoder() {
        encoderWinch.reset();
        encoderWinch.start();
    }

    public void turnOnWinchMotor(double speed) {
        winchMotor.set(speed);
    }

    public void turnOffWinchMotor() {
        winchMotor.set(0.0);
    }

    public boolean encoderLimitReached(int limit) {
//        System.out.println("encoderWinch/limit: " + encoderWinch.get() + "/" + limit);
        return (encoderWinch.get() >= limit);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new ArmWithTrigger());    // Always look to arm with the trigger.

        setDefaultCommand(new SetSetPointWithTrigger());
    }

    public void updateStatus() {
        SmartDashboard.putInt("Winch Encoder:", encoderWinch.get());
        SmartDashboard.putInt("Cannon Set Point:", cannonSetPoint);
        SmartDashboard.putBoolean("clutchSolenoidRelease: ", clutchSolenoidRelease.get());
        SmartDashboard.putBoolean("clutchSolenoidHold: ", clutchSolenoidHold.get());
    }

    // Arm the kicker using the right trigger of the xBox controller.
    // UNUSED.
    public void armWithTrigger(Joystick xBox) {
        double triggerValue = xBox.getAxis(Joystick.AxisType.kZ);
        if ((triggerValue < 0) // 0 -> -1 is the right trigger on the xBox
                && !encoderLimitReached(ENCODER_MAX_VALUE)) {
            winchMotor.set(-triggerValue * MOTOR_SCALE_FACTOR);
        } else {
            winchMotor.set(0.0);
        }
    }

    public void zeroSetPoint() {
        cannonSetPoint = 0;
    }

    public void setSetPointWithTrigger(Joystick xBox) {
        double triggerValue = xBox.getAxis(Joystick.AxisType.kZ);
        cannonSetPoint += -triggerValue * 10;
    }
}
