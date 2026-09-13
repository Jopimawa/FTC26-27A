package org.firstinspires.ftc.teamcode.subsystem;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;

// every moving part related to cell scoring -jr
public class CellSubsystem extends SubsystemBase {
    private final Telemetry telemetry;
    public CellSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
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
