package org.firstinspires.ftc.teamcode.core.auto.test;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.command.DriveMove;
import org.firstinspires.ftc.teamcode.subsystem.DriveSubsystem;

@Configurable
@TeleOp(name="Move Test", group="Auto")
public class AutoMoveTest extends CommandOpMode {
    public static double speed = 1;
    public static double time = 100;
    private DriveSubsystem drive;
    @Override
    public void initialize() {
        tutorial();
        drive = new DriveSubsystem(hardwareMap);

        GamepadEx gamepad = new GamepadEx(gamepad1);
        gamepad.getGamepadButton(GamepadKeys.Button.A)
                .whenPressed(new DriveMove(drive, speed, 0, 0).withTimeout((long) time).andThen(new RunCommand(this::info)));
    }
    public void tutorial(){
        telemetry.addLine("Test the move command: MoveLeft + MoveRight");
        telemetry.addLine("These routines have no pedropathing");
        telemetry.addLine("TESTING:");
        telemetry.addLine("1. Connect to robot wifi RC8796 on laptop");
        telemetry.addLine("2. search up 192.168.49.1:8001 on browser");
        telemetry.addLine("3. Open the AutoMoveTest tab in the middle; this is where you test");
        telemetry.addLine("4. Press A to run the command");
        telemetry.addLine("DONE:");
        telemetry.addLine("1. Open android studio on laptop");
        telemetry.addLine("2. core>auto>AutoMoveLeft or AutoMoveRight");
        telemetry.addLine("3. change the speed and time variable at the top to set it");

        telemetry.update();
    }
    public void info(){
        telemetry.addData("Speed (-1 to 1): ",speed);
        telemetry.addData("Time: ", String.valueOf(time)," ms");
        tutorial();
    }

}
