package org.firstinspires.ftc.teamcode.subsystem;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class GameSubsystem extends SubsystemBase {
    private final DcMotorEx intake;
    public GameSubsystem(HardwareMap hardwareMap) {
        intake = (DcMotorEx) hardwareMap.get(DcMotor.class, "intake");
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void setIntake(double power) {
        intake.setPower(power);
    }
    public void stopIntake() {
        intake.setPower(0);
    }

}
