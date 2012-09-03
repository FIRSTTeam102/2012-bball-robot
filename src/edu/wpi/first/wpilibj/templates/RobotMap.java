package edu.wpi.first.wpilibj.templates;

import Team102Lib.*;
import edu.wpi.first.wpilibj.DriverStationLCD;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.

    // PWM Ports
    public static final int rightMotor = 1;
    public static final int leftMotor = 2;
    public static final int winchMotorPort = 3;
    public static final int tilterArmMotorPort = 4;
    public static final int conveyorMotorPort = 5;

    // Analog Input Ports
    public static final int gyroPort = 1;
//    public static final int ultrasonic = 2;

    // Joystick Setup
    public static final double joystickRange = 1.0d; // the range of the joystick around 0.0
    public static final double flatDeadband = 0.02d;        // The amount of flat space in the deadband (around 0.0)
    public static Deadband stickDeadBand = null;    // Used to create a smooth deadband for the stick.
    public static Deadband conveyorDeadBand = null;    // Used to create a smooth deadband for the twist.
    public static final double twistCorrection = +0.00;

    //Digital Input / Outputs
    public static final int winchEncoderPortA = 2;
    public static final int winchEncoderPortB = 4;
    public static final int compressorSensorChannel = 1;

    // Relay Ports
    public static final int compressorSwitchChannel = 1;
    public static final int cameraLightsSwitchChannel = 8;

    // Solenoid Modules
    public static final int solenoidModule = 2;
    public static final int clutchSolenoidReleasePort = 3;
    public static final int clutchSolenoidHoldPort = 4;
    public static final int gateSolenoidUpPort = 1;
    public static final int gateSolenoidDownPort = 2;
    public static final int shiftSolenoidHigh = 5;
    public static final int shiftSolenoidLow = 6;

    // I2C ports
    public static final int accelerometerPort = 1;

    public static final int autonomousDisabled = 1;
    public static final int autonomousCenter = 2;
    public static final int autonomousLeft = 3;
    public static final int autonomousRamp = 4;

    // LCD Display Positions
    public static final DriverStationLCD.Line SetPointLCDLine = DriverStationLCD.Line.kUser2;
    public static final int SetPointLCDCol = 1;

    public static final DriverStationLCD.Line WinchEncoderLCDLine = DriverStationLCD.Line.kUser2;
    public static final int WinchEncoderLCDCol = DriverStationLCD.kLineLength / 2;

    public static final DriverStationLCD.Line GyroLCDLine = DriverStationLCD.Line.kUser3;
    public static final int GyroLCDCol = 1;

    public static final DriverStationLCD.Line TargetDistLCDLine = DriverStationLCD.Line.kUser4;
    public static final int TargetDistLCDCol = 1;

    public static final DriverStationLCD.Line TargetPosLCDLine = DriverStationLCD.Line.kUser4;
    public static final int TargetPosLCDCol = DriverStationLCD.kLineLength / 2;

    public static final DriverStationLCD.Line TargetSetPointLCDLine = DriverStationLCD.Line.kUser5;
    public static final int TargetSetPointLCDCol = 1;
}
