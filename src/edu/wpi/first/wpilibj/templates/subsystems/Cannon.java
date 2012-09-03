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

    final int ENCODER_MAX_VALUE = 700;
    final double MOTOR_SCALE_FACTOR = 0.7;
    public static final double TIME_TO_RELEASE_CLUTCH = 0.4;

    public int kickerSetPoint = 0;
    public Encoder encoderWinch; // = new Encoder(RobotMap.winchEncoderPortA, RobotMap.winchEncoderPortB);
    Victor winchMotor;
    Solenoid solenoid1;
    Solenoid solenoid2;
    Solenoid clutchSolenoidRelease;
    Solenoid clutchSolenoidHold;

    public Cannon() {
        winchMotor = new Victor(RobotMap.winchMotorPort);
        solenoid1 = new Solenoid(RobotMap.solenoidModule, 1);
        solenoid2 = new Solenoid(RobotMap.solenoidModule, 2);
        clutchSolenoidRelease = new Solenoid(RobotMap.solenoidModule, RobotMap.clutchSolenoidReleasePort);
        clutchSolenoidHold = new Solenoid(RobotMap.solenoidModule, RobotMap.clutchSolenoidHoldPort);
        encoderWinch = new Encoder(RobotMap.winchEncoderPortA, RobotMap.winchEncoderPortB);
    }

    public void engageSprings() {
        solenoid1.set(false);
        solenoid2.set(true);
    }

    public void disengageSprings() {
        solenoid1.set(true);
        solenoid2.set(false);
    }

    public void engageClutch() {
        clutchSolenoidRelease.set(false);
        clutchSolenoidHold.set(true);
    }

    public void disengageClutch() {
        clutchSolenoidRelease.set(true);
        clutchSolenoidHold.set(false);
        zeroEncoder();                      // Auto zero the encoder after releasing to prevent overwinding.
    }

    public void zeroEncoder() {
        encoderWinch.reset();
        encoderWinch.start();
    }

    public void turnOnWinchMotor() {
        System.out.println("Turning on winch Motor");
        engageClutch();
        winchMotor.set(0.70);
    }

    public void turnOffWinchMotor() {
        System.out.println("Turning off winch Motor");
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
        SmartDashboard.putInt("Kicker Set Point:", kickerSetPoint);
    }

    // Arm the kicker using the right trigger of the xBox controller.
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
        kickerSetPoint = 0;
    }

    public void setSetPointWithTrigger(Joystick xBox) {
        double triggerValue = xBox.getAxis(Joystick.AxisType.kZ);
        kickerSetPoint += -triggerValue * 10;
    }
}
