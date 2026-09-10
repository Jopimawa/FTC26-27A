package org.firstinspires.ftc.teamcode.core;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.command.IntakeMove;
import org.firstinspires.ftc.teamcode.command.DriveMove;
import org.firstinspires.ftc.teamcode.command.DriveRawMove;
import org.firstinspires.ftc.teamcode.command.DriveToggleEncoders;
import org.firstinspires.ftc.teamcode.subsystem.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.IntakeSubsystem;

public class RobotContainer {
    private final DriveSubsystem drive;
    private final IntakeSubsystem game;
    private final GamepadEx[] gamepad;
    private Telemetry telemetry;
    public RobotContainer(HardwareMap hardwareMap, Telemetry telemetry, Gamepad gamepad1, Gamepad gamepad2) {
        drive = new DriveSubsystem(hardwareMap, telemetry);
        game = new IntakeSubsystem(hardwareMap);
        gamepad = new GamepadEx[]{new GamepadEx(gamepad1), new GamepadEx(gamepad2)};
        configureBindings();
    }
    public void configureBindings() {
        drive.setDefaultCommand(new DriveMove(drive, gamepad[0]::getLeftX, gamepad[0]::getLeftY, gamepad[0]::getRightX));

        gamepad[0].getGamepadButton(GamepadKeys.Button.A)
                  .whenHeld(new IntakeMove(game,1));
        gamepad[0].getGamepadButton(GamepadKeys.Button.X)
                .whenHeld(new DriveToggleEncoders(drive, true));
        gamepad[0].getGamepadButton(GamepadKeys.Button.Y)
                .whenHeld(new DriveToggleEncoders(drive, false));

        gamepad[0].getGamepadButton(GamepadKeys.Button.DPAD_UP)
                  .whenHeld(new DriveRawMove(drive,0.05,0.05,0.05,0.05));
        gamepad[0].getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                  .whenHeld(new DriveRawMove(drive,0,0.5,0,0));
        gamepad[0].getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                  .whenHeld(new DriveRawMove(drive,0.5,0,0.5,0));
        gamepad[0].getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
                  .whenHeld(new DriveRawMove(drive,0.5,0,0,0.5));
    }
}
