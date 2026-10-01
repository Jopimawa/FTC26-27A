package org.firstinspires.ftc.teamcode.command;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.CellSubsystem;

public class CellThrow extends CommandBase {
    private final CellSubsystem cell;
    private final double speed;
    public CellThrow(CellSubsystem cell, double speed) {
        this.cell = cell;
        this.speed = speed;
    }

    @Override
    public void execute() {
        cell.setFlywheel(speed);
    }
    @Override
    public void end(boolean interrupted) {
        cell.stopFlywheel();
    }

}
