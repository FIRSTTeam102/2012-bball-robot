/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.templates.commands.DriveWithJoysticks;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Gyro;
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
    SpeedController leftFront;
    SpeedController leftRear;
    SpeedController rightFront;
    SpeedController rightRear;
    RobotDrive drive;
    public Gyro gyro;
    double righty;
    double lefty;


    // Initialize your subsystem here
    public DriveTrain() {
        super("DriveTrain", Kp, Ki, Kd);
        leftFront = new Victor(RobotMap.frontLeftMotor);
        leftRear = new Victor(RobotMap.rearLeftMotor);
        rightFront = new Victor(RobotMap.frontRightMotor);
        rightRear = new Victor(RobotMap.rearRightMotor);
        drive = new RobotDrive(leftFront, leftRear, rightFront, rightRear);
        drive.setSafetyEnabled(false);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);

        // Gyro setup
        gyro = new Gyro(RobotMap.gyroPort);
        gyro.reset();

        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.


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

        righty = rightstick.getY();
        lefty = leftstick.getY();

        righty = RobotMap.stickDeadBand.Deaden(righty);
        lefty = RobotMap.stickDeadBand.Deaden(lefty);

        drive.tankDrive(lefty, righty);
    }

    public void updateStatus() {
        SmartDashboard.putDouble("Gyro: ", gyro.getAngle());
        SmartDashboard.putDouble("Gyro Rem: ", (gyro.getAngle() % 360));
        SmartDashboard.putDouble("P: ", this.getPIDController().getP());
        SmartDashboard.putDouble("I: ", this.getPIDController().getI());
        SmartDashboard.putDouble("D: ", this.getPIDController().getD());
        SmartDashboard.putDouble("PID Error: ", this.getPIDController().getError());
     }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return gyro.getAngle();
    }

    protected void usePIDOutput(double output) {
        leftRear.set(output);
        leftFront.set(output);
        rightRear.set(-output);
        rightFront.set(-output);


        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    }
}