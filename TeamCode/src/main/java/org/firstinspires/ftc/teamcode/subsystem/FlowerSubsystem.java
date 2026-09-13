package org.firstinspires.ftc.teamcode.subsystem;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.CRServo;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

// Every moving part related to flower scoring -jr
public class FlowerSubsystem extends SubsystemBase {
    private final Telemetry telemetry;
    public FlowerSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
    }
    public void set(double power) {
    }
    public void stop() {
    }

    @Override
    public void periodic() {
       telemetry.update();
    }
}
