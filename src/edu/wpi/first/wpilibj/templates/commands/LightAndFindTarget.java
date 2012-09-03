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
public class LightAndFindTarget extends CommandGroup {

    public LightAndFindTarget() {
        addSequential(new LightsOn());
        addSequential(new WaitCommand(1.0));
        addSequential(new FindTarget());
        addSequential(new LightsOff());
    }
}