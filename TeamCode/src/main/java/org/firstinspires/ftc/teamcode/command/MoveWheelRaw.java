package org.firstinspires.ftc.teamcode.command;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.DriveSubsystem;

import java.util.function.DoubleSupplier;

public class MoveWheelRaw extends CommandBase {
    DriveSubsystem drive;
    double fl;
    double fr;
    double bl;
    double br;
    public MoveWheelRaw(DriveSubsystem drive, double fl, double fr, double bl, double br) {
        this.drive = drive;
        this.fl = fl;
        this.fr = fr;
        this.bl = bl;
        this.br = br;
        addRequirements(drive);
    }

    @Override
    public void initialize() {
        drive.updateK();
    }

    @Override
    public void execute() {
        drive.driveRaw(fl,fr,bl,br);
    }

    @Override
    public void end(boolean interrupted) { drive.stop(); }
}
