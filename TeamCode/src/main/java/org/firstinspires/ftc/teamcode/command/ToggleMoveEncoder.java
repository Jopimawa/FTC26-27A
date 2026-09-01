package org.firstinspires.ftc.teamcode.command;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.DriveSubsystem;

import java.util.function.DoubleSupplier;

public class ToggleMoveEncoder extends CommandBase {
    DriveSubsystem drive;
    boolean vel;
    public ToggleMoveEncoder(DriveSubsystem drive, boolean vel) {
        this.drive = drive;
        this.vel = vel;
    }

    @Override
    public void initialize() {
        drive.updateV(vel);
    }

}
