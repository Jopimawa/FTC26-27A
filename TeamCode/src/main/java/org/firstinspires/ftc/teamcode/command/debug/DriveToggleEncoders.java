package org.firstinspires.ftc.teamcode.command.debug;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.DriveSubsystem;

public class DriveToggleEncoders extends CommandBase {
    DriveSubsystem drive;
    boolean vel;
    public DriveToggleEncoders(DriveSubsystem drive, boolean vel) {
        this.drive = drive;
        this.vel = vel;
    }

    @Override
    public void initialize() {
        drive.setV(vel);
    }

}
