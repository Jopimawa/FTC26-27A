package org.firstinspires.ftc.teamcode.subsystem;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

public class GameSubsystem extends SubsystemBase {
    private final Motor intake;
    public GameSubsystem(HardwareMap hardwareMap) {
        intake = new Motor(hardwareMap, "intake");
    }
    public void setIntake(double power) {
        intake.set(power);
    }
    public void stopIntake() {
        intake.stopMotor();
    }

}
