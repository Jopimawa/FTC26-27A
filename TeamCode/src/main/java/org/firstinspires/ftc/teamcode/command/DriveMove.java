package org.firstinspires.ftc.teamcode.command;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.DriveSubsystem;

import java.util.function.DoubleSupplier;

public class DriveMove extends CommandBase {
    DriveSubsystem drive;
    DoubleSupplier x;
    DoubleSupplier y;
    DoubleSupplier rx;
    public DriveMove(DriveSubsystem drive, DoubleSupplier x, DoubleSupplier y, DoubleSupplier rx) {
        this.drive = drive;
        this.x = x;
        this.y = y;
        this.rx = rx;
        addRequirements(drive);
    }
    public DriveMove(DriveSubsystem drive, double x, double y, double rx) {
        this(drive, () -> x, () -> y, () -> rx);
    }

    @Override
    public void initialize() {
        drive.setK();
    }

    @Override
    public void execute() {
        drive.setDrive(x.getAsDouble(), y.getAsDouble(), rx.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) { drive.stopDrive(); }
}
