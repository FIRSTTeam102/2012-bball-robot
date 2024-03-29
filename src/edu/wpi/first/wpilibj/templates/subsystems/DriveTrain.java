/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import Team102Lib.MathLib;
import Team102Lib.MessageLogger;
import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.ADXL345_I2C.DataFormat_Range;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.templates.commands.DriveWithJoysticks;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.templates.commands.DriveWithXbox;

/**
 *
 * @author Administrator
 */
public class DriveTrain extends PIDSubsystem
{
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
    double preRightJoyY;
    double preLeftJoyY;
    double rightJoyY;
    double leftJoyY;

    // Initialize your subsystem here
    public DriveTrain()
    {
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

        accelerometer = new ADXL345_I2C(RobotMap.accelerometerPort, DataFormat_Range.k2G);

    }

    public void enableGyroSetPoint(double setPoint)
    {
        setSetpoint(setPoint);
        enable();
    }

    public void disablePIDMovement()
    {
        disable();
    }

    public void initDefaultCommand()
    {
        setDefaultCommand(new DriveWithXbox());
    }

    public void driveWithJoysticks(Joystick leftstick, Joystick rightstick)
    {

        preRightJoyY = rightstick.getY();
        preLeftJoyY = leftstick.getY();

        rightJoyY = RobotMap.stickDeadBand.Deaden(preRightJoyY);
        leftJoyY = RobotMap.stickDeadBand.Deaden(preLeftJoyY);

//        System.out.println("Left Joy: " + -leftJoyY);
//        System.out.println("Right Joy: " + rightJoyY);
        drive.tankDrive(-leftJoyY, rightJoyY);
    }

    public void driveWithXbox(Joystick xbox)
    {

        double preRighty = xbox.getRawAxis(RobotMap.xBoxRightYAxis);
        double preLefty = xbox.getRawAxis(RobotMap.xBoxLeftYAxis);

        double righty = RobotMap.stickDeadBand.Deaden(preRighty);
        double lefty = RobotMap.stickDeadBand.Deaden(preLefty);

      //  MessageLogger.LogMessage("XBox\t" + preRighty + "\t" + preLefty + "\t" + righty + "\t" + lefty);
        drive.tankDrive(-lefty, righty);
    }

    public void updateStatus()
    {
//        SmartDashboard.putDouble("Gyro: ", gyro.getAngle());
//        ADXL345_I2C.AllAxes accs = accelerometer.getAccelerations();
//        SmartDashboard.putDouble("AcclX: ", accs.XAxis);
//        SmartDashboard.putDouble("AcclY: ", accs.YAxis);
//        SmartDashboard.putDouble("AcclZ: ", accs.ZAxis);
//        SmartDashboard.putDouble("P: ", this.getPIDController().getP());
//        SmartDashboard.putDouble("I: ", this.getPIDController().getI());
//        SmartDashboard.putDouble("D: ", this.getPIDController().getD());
//        SmartDashboard.putDouble("PID Error: ", this.getPIDController().getError());
//        SmartDashboard.putDouble("leftDriveMotor: ", leftMotor.get());
//        SmartDashboard.putDouble("rightDriveMotor: ", rightMotor.get());
//        SmartDashboard.putDouble("rightJoyY: ", rightJoyY);
//        SmartDashboard.putDouble("leftJoyY: ", -leftJoyY);

        MessageLogger.WriteToLCD(RobotMap.GyroLCDLine, RobotMap.GyroLCDCol, "Gy: " + MathLib.round(gyro.getAngle(), 1));
//        MessageLogger.LogMessage(preLeftJoyY + "\t" + preRightJoyY + "\t" + -leftJoyY + "\t" + rightJoyY + "\t" + leftMotor.get() + "\t" + rightMotor.get());
    }

    protected double returnPIDInput()
    {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return gyro.getAngle();
    }

    protected void usePIDOutput(double output)
    {
        leftRear.set(output);
        leftMotor.set(output);
        rightRear.set(-output);
        rightMotor.set(-output);
    }

    public void drive(double speed)
    {
        drive.tankDrive(-speed, speed);
    }
}
