package org.firstinspires.ftc.teamcode.subsystem;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.helper.MotorInit;

// every moving part related to cell scoring -jr
public class CellSubsystem extends SubsystemBase {
    private static Telemetry telemetry;
    private static Motor m_flywheel;
    public CellSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        // 6k RPM be careful
        m_flywheel = new Motor(hardwareMap, "flywheel");
        MotorInit.setupMotor(m_flywheel,false, false, false, new double[] {0});
        CellSubsystem.telemetry = telemetry;
    }
    public void setFlywheel(double power) {
        m_flywheel.set(power);
    }
    public void stopFlywheel() {
        m_flywheel.stopMotor();
    }

    @Override
    public void periodic() {
       telemetry.update();
    }
}
