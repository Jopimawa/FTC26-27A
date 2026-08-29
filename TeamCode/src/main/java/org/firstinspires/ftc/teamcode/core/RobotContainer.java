package org.firstinspires.ftc.teamcode.core;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.Command;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.command.MoveIntake;
import org.firstinspires.ftc.teamcode.command.MoveWheel;
import org.firstinspires.ftc.teamcode.command.MoveWheelRaw;
import org.firstinspires.ftc.teamcode.subsystem.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.GameSubsystem;

public class RobotContainer {
    private final DriveSubsystem drive;
    private final GameSubsystem game;
    private final GamepadEx[] controller;
    private Telemetry telemetry;
    public RobotContainer(HardwareMap hardwareMap, Telemetry telemetry, Gamepad controller1, Gamepad controller2) {
        drive = new DriveSubsystem(hardwareMap);
        game = new GameSubsystem(hardwareMap);
        controller = new GamepadEx[]{new GamepadEx(controller1), new GamepadEx(controller2)};
        configureBindings();
    }
    public void configureBindings() {
        drive.setDefaultCommand(new MoveWheel(drive, controller[0]::getLeftX, controller[0]::getLeftY, controller[0]::getRightX));

        controller[0].getGamepadButton(GamepadKeys.Button.A).whenHeld(new MoveIntake(game,1));

        controller[0].getGamepadButton(GamepadKeys.Button.DPAD_UP).whenHeld(new MoveWheelRaw(drive,0.5,0,0,0));
        controller[0].getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whenHeld(new MoveWheelRaw(drive,0,0.5,0,0));
        controller[0].getGamepadButton(GamepadKeys.Button.DPAD_RIGHT).whenHeld(new MoveWheelRaw(drive,0.5,0,0.5,0));
        controller[0].getGamepadButton(GamepadKeys.Button.DPAD_LEFT).whenHeld(new MoveWheelRaw(drive,0.5,0,0,0.5));
    }
}
