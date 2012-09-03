/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 * @author Administrator
 */
public class GateDownThenUp extends CommandGroup {

    public GateDownThenUp() {
        addSequential(new GateDown());
        addSequential(new WaitCommand(0.5));
        addSequential(new GateUp());
    }
}