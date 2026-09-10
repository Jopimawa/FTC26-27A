package org.firstinspires.ftc.teamcode.subsystem;


import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.helper.MotorInit;

@Configurable
public class DriveSubsystem extends SubsystemBase {

    private static boolean setup = false;
    private static Telemetry telemetry;
    Motor m_frontLeft;
    public static boolean frontLeftRev = false;
    Motor m_frontRight;
    public static boolean frontRightRev = true;
    Motor m_backLeft;
    public static boolean backLeftRev = false;
    Motor m_backRight;
    public static boolean backRightRev = true;
    public static double[] driveK = new double[] {0.5, 0, 0.022, 0.02, 1.2};
    private boolean currVel;
    public DriveSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {

        m_frontLeft = new Motor(hardwareMap,"frontLeft");

        m_frontRight = new Motor(hardwareMap,"frontRight");
        MotorInit.setupMotor(m_frontRight,frontRightRev,true,true, driveK);

        m_backLeft = new Motor(hardwareMap,"backLeft");
        MotorInit.setupMotor(m_backLeft,backLeftRev,true,true, driveK);

        m_backRight = new Motor(hardwareMap,"backRight");
        MotorInit.setupMotor(m_backRight,backRightRev,true,true, driveK);

        DriveSubsystem.telemetry = telemetry;
    }
    public void setupMotor(Motor motor, boolean isRev, boolean isBrake, boolean isVelocityControl, double[] k) {
        motor.setInverted(isRev);
        Motor.ZeroPowerBehavior brake = (isBrake) ? Motor.ZeroPowerBehavior.BRAKE : Motor.ZeroPowerBehavior.FLOAT;
        motor.setZeroPowerBehavior(brake);
        MotorInit.setV(motor, isVelocityControl);
        MotorInit.setK(motor, k);
    }
    public void setV(boolean isVelocityControl) {
        currVel = isVelocityControl;
        MotorInit.setV(m_frontLeft, isVelocityControl);
        MotorInit.setV(m_frontRight, isVelocityControl);
        MotorInit.setV(m_backLeft, isVelocityControl);
        MotorInit.setV(m_backRight, isVelocityControl);
    }

    public void setK() {
        MotorInit.setK(m_frontLeft, driveK);
        MotorInit.setK(m_frontRight, driveK);
        MotorInit.setK(m_backLeft, driveK);
        MotorInit.setK(m_backRight, driveK);
    }
    public void setDrive(double x, double y, double rx) {
        driveManual(x,y,rx);
        setup = true;
        telemetry.addData("x  ",x);
        telemetry.addData("y  ",y);
        telemetry.addData("rx ",rx);
    }

    public void driveManual(double x, double y, double rx) {
       double theta = Math.atan2(y,x);
       double power = Math.hypot(y,x);

       double sin = Math.sin(theta - Math.PI/4);
       double cos = Math.cos(theta - Math.PI/4);
       double max = Math.max(Math.abs(sin), Math.abs(cos));

       double frontLeft  = power * cos/max + rx;
       double frontRight = power * sin/max - rx;
       double backLeft   = power * sin/max + rx;
       double backRight  = power * cos/max - rx;

       if ((power + Math.abs(rx)) > 1) {
           frontLeft  /= power + Math.abs(rx);
           frontRight /= power + Math.abs(rx);
           backLeft   /= power + Math.abs(rx);
           backRight  /= power + Math.abs(rx);
       }

       setDrive(frontLeft,frontRight,backLeft,backRight);
    }

    public void setDrive(double fl, double fr, double bl, double br) {
        m_frontLeft.set(fl);
        m_frontRight.set(fr);
        m_backLeft.set(bl);
        m_backRight.set(br);
        telemetry.addData("fl ",fl);
        telemetry.addData("fr ",fr);
        telemetry.addData("bl ",bl);
        telemetry.addData("br ",br);

    }
    public void stopDrive() {
        m_frontLeft.stopMotor();
        m_backLeft.stopMotor();
        m_frontRight.stopMotor();
        m_backRight.stopMotor();
    }

    @Override
    public void periodic() {
        if (setup) {
            telemetry.addData("encoder usage: ", currVel);
            double flr = m_frontLeft.getRate();
            double frr = m_frontRight.getRate();
            double brr = m_backLeft.getRate();
            double blr = m_backRight.getRate();
            telemetry.addData("fl ", flr);
            telemetry.addData("fr ", frr);
            telemetry.addData("bl ", brr);
            telemetry.addData("br ", blr);
            double avg = (flr+frr+brr+blr)/4;
            double max = Math.max(flr, Math.max(frr, Math.max(brr, blr)));
            double min = Math.min(flr, Math.min(frr, Math.min(brr, blr)));
            double avgdiff = max-avg;
            double maxdiff = max-min;
            telemetry.addData("avg ", avg);
            telemetry.addData("adf ", avgdiff);
            telemetry.addData("mdf ", maxdiff);
            telemetry.update();
        }
    }

}
