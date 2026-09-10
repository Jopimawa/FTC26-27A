package org.firstinspires.ftc.teamcode.command;


import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.IntakeSubsystem;

public class IntakeMove extends CommandBase {
    private final IntakeSubsystem intake;
    private final double power;
    public IntakeMove(IntakeSubsystem intake, double power) {
        this.intake = intake;
        this.power = power;
    }


    @Override
    public void execute() {
        intake.setIntake(-power);
    }

    @Override
    public void end(boolean interrupted) {
        intake.stopIntake();
    }

}
