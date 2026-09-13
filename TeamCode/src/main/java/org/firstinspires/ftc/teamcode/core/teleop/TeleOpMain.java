package org.firstinspires.ftc.teamcode.core.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;

@TeleOp(name="TeleOp Main", group="Season")
public class TeleOpMain extends CommandOpMode {
    private RobotContainer robotContainer;

    @Override
    public void initialize() {
        robotContainer = new RobotContainer(hardwareMap, telemetry, gamepad1, gamepad2);
    }
}
