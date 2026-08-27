package org.firstinspires.ftc.teamcode.core;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.command.MoveIntake;
import org.firstinspires.ftc.teamcode.command.MoveWheel;
import org.firstinspires.ftc.teamcode.subsystem.DriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.GameSubsystem;

public class RobotContainer {
    private final DriveSubsystem drive;
    private final GameSubsystem game;
    private final GamepadEx controller1;
    private final GamepadEx controller2;
    private Telemetry telemetry;
    public RobotContainer(HardwareMap hardwareMap, Telemetry telemetry, Gamepad controller1, Gamepad controller2) {
        //drive = new DriveSubsystem(hardwareMap);
        game = new GameSubsystem(hardwareMap);
        this.controller1 = new GamepadEx(controller1);
        this.controller2 = new GamepadEx(controller2);
        configureBindings();
    }
    public void configureBindings() {
        //drive.setDefaultCommand(new MoveWheel(drive, controller1::getLeftX, controller1::getLeftY, controller1::getRightX));

        controller1.getGamepadButton(GamepadKeys.Button.A).whenHeld(new MoveIntake(game,1));
    }
}
