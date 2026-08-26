package org.firstinspires.ftc.teamcode.command;


import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.GameSubsystem;

public class MoveIntake extends CommandBase {
    private final GameSubsystem intake;
    private final double power;
    public MoveIntake(GameSubsystem intake, double power) {
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
