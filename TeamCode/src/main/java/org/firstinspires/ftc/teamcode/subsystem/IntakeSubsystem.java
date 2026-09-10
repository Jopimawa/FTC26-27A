package org.firstinspires.ftc.teamcode.subsystem;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

public class IntakeSubsystem extends SubsystemBase {
    private final Motor m_intake;
    public IntakeSubsystem(HardwareMap hardwareMap) {
        m_intake = new Motor(hardwareMap, "intake");
    }
    public void setIntake(double power) {
        m_intake.set(power);
    }
    public void stopIntake() {
        m_intake.stopMotor();
    }

}
