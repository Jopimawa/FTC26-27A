package org.firstinspires.ftc.teamcode.helper;

import com.seattlesolvers.solverslib.hardware.motors.Motor;

public static class MotorInit {
    public static Motor setupMotor(Motor motor, boolean isRev, boolean isBrake, boolean isVelocityControl, double[] k) {
        motor.setInverted(isRev);
        Motor.ZeroPowerBehavior brake = (isBrake) ? Motor.ZeroPowerBehavior.BRAKE : Motor.ZeroPowerBehavior.FLOAT;
        motor.setZeroPowerBehavior(brake);
        Motor.RunMode runMode = (isVelocityControl) ? Motor.RunMode.VelocityControl : Motor.RunMode.RawPower;
        motor.setRunMode(runMode);
        if (isVelocityControl)
            MotorInit.setK(motor, k);
    }
    public static void setV(Motor motor, boolean isVelocityControl) {
        Motor.RunMode runMode = (isVelocityControl) ? Motor.RunMode.VelocityControl : Motor.RunMode.RawPower;
        motor.setRunMode(runMode);
    }
    public static void setK(Motor motor, double[] coeff) {

    }
}
