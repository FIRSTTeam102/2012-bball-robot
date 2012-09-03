package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.templates.OI;
import edu.wpi.first.wpilibj.templates.subsystems.BallGate;
import edu.wpi.first.wpilibj.templates.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.templates.subsystems.Cannon;
import edu.wpi.first.wpilibj.templates.subsystems.Conveyor;
import edu.wpi.first.wpilibj.templates.subsystems.Pnuematics;
import edu.wpi.first.wpilibj.templates.subsystems.Shifters;
import edu.wpi.first.wpilibj.templates.subsystems.TilterArm;
import edu.wpi.first.wpilibj.templates.subsystems.Vision;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author Author
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    // Create a single static instance of all of your subsystems
   // public static Chassis chassis = new Chassis();
    public static Pnuematics pnuematics = new Pnuematics();
    public static Cannon cannon = new Cannon();
    public static DriveTrain driveTrain = new DriveTrain();
    public static BallGate ballGate = new BallGate();
    public static TilterArm tilterArm = new TilterArm();
    public static Conveyor conveyor = new Conveyor();
    public static Vision vision = new Vision();
    public static Shifters shifters = new Shifters();

    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();

        // Show what command your subsystem is running on the SmartDashboard
        SmartDashboard.putData(ballGate);
        SmartDashboard.putData(cannon);
        SmartDashboard.putData(conveyor);
        SmartDashboard.putData(driveTrain);
        SmartDashboard.putData(pnuematics);
        SmartDashboard.putData(tilterArm);
        SmartDashboard.putData(shifters);
        SmartDashboard.putData(vision);

        // Pnuematics Commands
        SmartDashboard.putData(new CompressorOn());
        SmartDashboard.putData(new CompressorOff());

        // Cannon Commands
        SmartDashboard.putData(new EngageClutch(true));
        SmartDashboard.putData("Disengage Clutch", new EngageClutch(false));
        SmartDashboard.putData(new RemoveCannonSlack());
        SmartDashboard.putData(new RemoveCannonSlackwDiff());
        SmartDashboard.putData(new ArmCannon());
        SmartDashboard.putData(new RemoveSlackAndArm());
        SmartDashboard.putData(new RemoveSlackwDiffAndArm());
        SmartDashboard.putData(new Shoot());

        // Gate Commands
        SmartDashboard.putData(new GateUp());
        SmartDashboard.putData(new GateDown());

        // Tilter Commands
        SmartDashboard.putData(new TilterArmUp());
        SmartDashboard.putData(new TilterArmDown());

        // Conveyor Commands
//        SmartDashboard.putData("Conveyor Forward", new DriveConveyor(Conveyor.CONVEYOR_MOTOR_SCALE));
//        SmartDashboard.putData("Conveyor Reverse", new DriveConveyor(-Conveyor.CONVEYOR_MOTOR_SCALE));

        // Drive Train commands
        SmartDashboard.putData(new TurnToAngle());
        SmartDashboard.putData(new ShiftHigh());
        SmartDashboard.putData(new ShiftLow());

        // Vision Commands
        SmartDashboard.putData(new LightAndFindTarget());
        SmartDashboard.putData(new LightsOn());
        SmartDashboard.putData(new LightsOff());

    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}


