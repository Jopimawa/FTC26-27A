package org.firstinspires.ftc.teamcode.subsystem;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

public class OuttakeSubsystem extends SubsystemBase {
    //private final Motor m_outtake;
    public OuttakeSubsystem(HardwareMap hardwareMap) {
        //m_outtake = new Motor(hardwareMap, "intake");
    }
    public void setOuttake(double power) {
        //m_outtake.set(power);
    }
    public void stopOuttake() {
        //m_outtake.stopMotor();
    }

}
