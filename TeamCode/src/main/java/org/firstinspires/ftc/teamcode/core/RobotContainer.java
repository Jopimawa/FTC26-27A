package org.firstinspires.ftc.teamcode.core;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.command.MoveIntake;
import org.firstinspires.ftc.teamcode.command.MoveWheel;
import org.firstinspires.ftc.teamcode.command.MoveWheelRaw;
import org.firstinspires.ftc.teamcode.command.ToggleMoveEncoder;
import org.firstinspires.ftc.teamcode.subsystem.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.GameSubsystem;

public class RobotContainer {
    private final DriveSubsystem drive;
    private final GameSubsystem game;
    private final GamepadEx[] gamepad;
    private Telemetry telemetry;
    public RobotContainer(HardwareMap hardwareMap, Telemetry telemetry, Gamepad gamepad1, Gamepad gamepad2) {
        drive = new DriveSubsystem(hardwareMap, telemetry);
        game = new GameSubsystem(hardwareMap);
        gamepad = new GamepadEx[]{new GamepadEx(gamepad1), new GamepadEx(gamepad2)};
        configureBindings();
    }
    public void configureBindings() {
        drive.setDefaultCommand(new MoveWheel(drive, gamepad[0]::getLeftX, gamepad[0]::getLeftY, gamepad[0]::getRightX));

        gamepad[0].getGamepadButton(GamepadKeys.Button.A)
                  .whenHeld(new MoveIntake(game,1));
        gamepad[0].getGamepadButton(GamepadKeys.Button.X)
                .whenHeld(new ToggleMoveEncoder(drive, true));
        gamepad[0].getGamepadButton(GamepadKeys.Button.Y)
                .whenHeld(new ToggleMoveEncoder(drive, false));

        gamepad[0].getGamepadButton(GamepadKeys.Button.DPAD_UP)
                  .whenHeld(new MoveWheelRaw(drive,1,1,1,1));
        gamepad[0].getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                  .whenHeld(new MoveWheelRaw(drive,0,0.5,0,0));
        gamepad[0].getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                  .whenHeld(new MoveWheelRaw(drive,0.5,0,0.5,0));
        gamepad[0].getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
                  .whenHeld(new MoveWheelRaw(drive,0.5,0,0,0.5));
    }
}
