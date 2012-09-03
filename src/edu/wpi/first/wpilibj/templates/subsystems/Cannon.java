/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import Team102Lib.MessageLogger;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.SetSetPointWithTrigger;

/**
 *
 * @author Administrator
 */
public class Cannon extends Subsystem {

    public static final double MOTOR_DIRECTION = -1.0;
    public static final double WINCH_MOTOR_SPEED = MOTOR_DIRECTION * 1.0;
    public static final double TIME_TO_RELEASE_CLUTCH = 1.0;
    public static final double TIME_TO_REMOVE_SLACK = 1.0;  // NOTE: this is a maximum time.  We use the encoder to figure out when the slack is removed.
    public static final double REMOVE_SLACK_TIMEOUT = 1.5;
    public static final double WINCH_MOTOR_SLACK_SPEED = MOTOR_DIRECTION * 0.12;
    
    static final double MOTOR_SCALE_FACTOR = MOTOR_DIRECTION * 1.0;       // UNUSED.  Used when pulling back winch with joystick.
    static final double ENCODER_MAX_VALUE = 1000.0;

    public double cannonSetPoint = 640.0;
    public Encoder encoderWinch; // = new Encoder(RobotMap.winchEncoderPortA, RobotMap.winchEncoderPortB);
    Victor winchMotor;
    Solenoid clutchSolenoidHold;
    Solenoid clutchSolenoidRelease;
    public DigitalInput zeroSensor;

    public Cannon() {
        winchMotor = new Victor(RobotMap.winchMotorPort);
        clutchSolenoidHold = new Solenoid(RobotMap.solenoidModule, RobotMap.clutchSolenoidReleasePort);
        clutchSolenoidRelease = new Solenoid(RobotMap.solenoidModule, RobotMap.clutchSolenoidHoldPort);
        encoderWinch = new Encoder(RobotMap.winchEncoderPortA, RobotMap.winchEncoderPortB);

        zeroSensor = new DigitalInput(RobotMap.zeroSensorPort);

        engageClutch();
    }

    public void engageClutch() {
        clutchSolenoidHold.set(true);
        clutchSolenoidRelease.set(false);
    }

    public void disengageClutch() {
        clutchSolenoidHold.set(false);
        clutchSolenoidRelease.set(true);
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

    public boolean encoderLimitReached(double limit) {
//        System.out.println("encoderWinch/limit: " + encoderWinch.get() + "/" + limit);
        return (encoderWinch.get() >= (int) limit);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.

        setDefaultCommand(new SetSetPointWithTrigger());
//        setDefaultCommand(new ArmWithTrigger());
    }

    public void updateStatus() {
//        SmartDashboard.putInt("W Encoder:", encoderWinch.get());
//        SmartDashboard.putDouble("Winch Motor:", winchMotor.get());
//        SmartDashboard.putDouble("Cannon Set Point:", cannonSetPoint);
//        SmartDashboard.putBoolean("clutchSolenoidRelease: ", clutchSolenoidHold.get());
//        SmartDashboard.putBoolean("clutchSolenoidHold: ", clutchSolenoidRelease.get());
        MessageLogger.WriteToLCD(RobotMap.SetPointLCDLine, RobotMap.SetPointLCDCol
                , "SP: " + (int) cannonSetPoint);
        MessageLogger.WriteToLCD(RobotMap.WinchEncoderLCDLine, RobotMap.WinchEncoderLCDCol
                , "WE: " + encoderWinch.get());
//        MessageLogger.LogMessage("zeroSensor: " + zeroSensor.get());
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
        cannonSetPoint = 0.0;
    }

    public void setSetPointWithTrigger(Joystick xBox) {
        double triggerValue = -xBox.getAxis(Joystick.AxisType.kZ) * 5.0;

        if(cannonSetPoint + triggerValue < 0.0)
            return;
        if(cannonSetPoint + triggerValue < ENCODER_MAX_VALUE)
            cannonSetPoint += triggerValue;
    }
}
