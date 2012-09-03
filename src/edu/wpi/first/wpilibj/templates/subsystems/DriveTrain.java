/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.ADXL345_I2C.DataFormat_Range;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.templates.commands.DriveWithJoysticks;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 * @author Administrator
 */
public class DriveTrain extends PIDSubsystem {
    private static final double Kp = -.05;
    private static final double Ki = 20.0;
    private static final double Kd = 0.0;
    SpeedController leftMotor;
    SpeedController leftRear;
    SpeedController rightMotor;
    SpeedController rightRear;
    RobotDrive drive;
    public Gyro gyro;
    public ADXL345_I2C accelerometer;
    double rightJoyY;
    double leftJoyY;
    Solenoid shiftSolenoidLow;
    Solenoid shiftSolenoidHigh;

    // Initialize your subsystem here
    public DriveTrain() {
        super("DriveTrain", Kp, Ki, Kd);
        leftMotor = new Victor(RobotMap.leftMotor);
        rightMotor = new Victor(RobotMap.rightMotor);
        drive = new RobotDrive(leftMotor, rightMotor);
        drive.setSafetyEnabled(false);
/*        drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
*/
        // Gyro setup
        gyro = new Gyro(RobotMap.gyroPort);
        gyro.reset();

        accelerometer = new ADXL345_I2C(RobotMap.accelerometerPort, DataFormat_Range.k4G);

        shiftSolenoidLow = new Solenoid(RobotMap.solenoidModule, RobotMap.shiftSolenoidHigh);
        shiftSolenoidHigh = new Solenoid(RobotMap.solenoidModule, RobotMap.shiftSolenoidLow);

        shiftHigh();
    }

    public void enableGyroSetPoint(double setPoint) {
        setSetpoint(setPoint);
        enable();
    }

    public void disablePIDMovement() {
        disable();
    }
    
    public void initDefaultCommand() {
         setDefaultCommand(new DriveWithJoysticks());
    }

    public void driveWithJoysticks(Joystick leftstick, Joystick rightstick) {

        rightJoyY = rightstick.getY();
        leftJoyY = leftstick.getY();

        rightJoyY = RobotMap.stickDeadBand.Deaden(rightJoyY);
        leftJoyY = RobotMap.stickDeadBand.Deaden(leftJoyY);

        drive.tankDrive(-leftJoyY, rightJoyY);
    }

    public void updateStatus() {
        SmartDashboard.putDouble("Gyro: ", gyro.getAngle());
        SmartDashboard.putDouble("AcclX: ", accelerometer.getAcceleration(ADXL345_I2C.Axes.kX));
        SmartDashboard.putDouble("AcclY: ", accelerometer.getAcceleration(ADXL345_I2C.Axes.kY));
        SmartDashboard.putDouble("AcclZ: ", accelerometer.getAcceleration(ADXL345_I2C.Axes.kZ));
        SmartDashboard.putDouble("P: ", this.getPIDController().getP());
        SmartDashboard.putDouble("I: ", this.getPIDController().getI());
        SmartDashboard.putDouble("D: ", this.getPIDController().getD());
        SmartDashboard.putDouble("PID Error: ", this.getPIDController().getError());
        SmartDashboard.putBoolean("shiftSolenoidHigh: ", shiftSolenoidLow.get());
        SmartDashboard.putBoolean("shiftSolenoidLow: ", shiftSolenoidHigh.get());
        SmartDashboard.putDouble("leftDriveMotor: ", leftMotor.get());
        SmartDashboard.putDouble("rightDriveMotor: ", rightMotor.get());
        SmartDashboard.putDouble("rightJoyY: ", rightJoyY);
        SmartDashboard.putDouble("leftJoyY: ", leftJoyY);
     }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return gyro.getAngle();
    }

    protected void usePIDOutput(double output) {
        leftRear.set(output);
        leftMotor.set(output);
        rightRear.set(-output);
        rightMotor.set(-output);
    }
    public void shiftHigh()
    {
        shiftSolenoidLow.set(false);
        shiftSolenoidHigh.set(true);
    }
    public void shiftLow()
    {
        shiftSolenoidLow.set(true);
        shiftSolenoidHigh.set(false);
    }
}