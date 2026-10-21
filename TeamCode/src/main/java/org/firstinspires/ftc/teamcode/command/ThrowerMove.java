package org.firstinspires.ftc.teamcode.command;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.ThrowerSubsystem;

public class ThrowerMove extends CommandBase {
    private final ThrowerSubsystem thrower;
   private final double power;

    public ThrowerMove(ThrowerSubsystem thrower, double power) {
        this.thrower = thrower;
        this.power = power;

    }
    @Override
    public void initialize() {
        thrower.setFlywheel(power);
    }

    @Override
    public void end(boolean interrupted) {
        thrower.stopFlywheel();
    }

}
