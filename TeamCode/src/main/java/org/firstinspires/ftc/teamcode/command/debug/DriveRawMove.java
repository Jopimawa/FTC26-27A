package org.firstinspires.ftc.teamcode.command.debug;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.DriveSubsystem;

public class DriveRawMove extends CommandBase {
    DriveSubsystem drive;
    double fl;
    double fr;
    double bl;
    double br;
    public DriveRawMove(DriveSubsystem drive, double fl, double fr, double bl, double br) {
        this.drive = drive;
        this.fl = fl;
        this.fr = fr;
        this.bl = bl;
        this.br = br;
        addRequirements(drive);
    }

    @Override
    public void initialize() {
        drive.setK();
    }

    @Override
    public void execute() {
        drive.setDrive(fl,fr,bl,br);
    }

    @Override
    public void end(boolean interrupted) { drive.stopDrive(); }
}
