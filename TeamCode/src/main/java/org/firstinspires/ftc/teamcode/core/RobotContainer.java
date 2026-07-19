package org.firstinspires.ftc.teamcode.core;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.command.MoveWheel;
import org.firstinspires.ftc.teamcode.subsystem.DriveSubsystem;

public class RobotContainer {
    private final DriveSubsystem drive;
    private final GamepadEx controller1;
    private final GamepadEx controller2;
    private Telemetry telemetry;
    public RobotContainer(HardwareMap hardwareMap, Telemetry telemetry, Gamepad controller1, Gamepad controller2) {
        drive = new DriveSubsystem(hardwareMap);
        this.controller1 = new GamepadEx(controller1);
        this.controller2 = new GamepadEx(controller2);
        configureBindings();
    }
    public void configureBindings() {
        drive.setDefaultCommand(new MoveWheel(drive, controller1::getLeftX, controller1::getLeftY, controller1::getRightX));

    }
}
