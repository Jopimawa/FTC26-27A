package org.firstinspires.ftc.teamcode.subsystem;


import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.helper.MotorInit;

@Configurable
public class DriveSubsystem extends SubsystemBase {

    private static Telemetry telemetry;
    private static Motor m_frontLeft;
    private static Motor m_frontRight;
    private static Motor m_backLeft;
    private static Motor m_backRight;
    public static boolean k_frontLeftInv = false;
    public static boolean k_frontRightInv = true;
    public static boolean k_backLeftInv = false;
    public static boolean k_backRightInv = true;
    public static double[] k_coeff = new double[] {0.5, 0, 0.022, 0.02, 1.2};
    //private static boolean setup = false;
    private boolean k_vel = true;
    public DriveSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {

        m_frontLeft = new Motor(hardwareMap,"frontLeft");
        MotorInit.setupMotor(m_frontLeft, k_frontLeftInv,true,true, k_coeff);

        m_frontRight = new Motor(hardwareMap,"frontRight");
        MotorInit.setupMotor(m_frontRight, k_frontRightInv,true,true, k_coeff);

        m_backLeft = new Motor(hardwareMap,"backLeft");
        MotorInit.setupMotor(m_backLeft, k_backLeftInv,true,true, k_coeff);

        m_backRight = new Motor(hardwareMap,"backRight");
        MotorInit.setupMotor(m_backRight, k_backRightInv,true,true, k_coeff);

        DriveSubsystem.telemetry = telemetry;
    }

    public void setDrive(double x, double y, double rx) {
        driveManual(x,y,rx);

        telemetry.addData("x  ",x);
        telemetry.addData("y  ",y);
        telemetry.addData("rx ",rx);
        //setup = true;
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

    public void driveManual(double x, double y, double rx) {
        //copied from a brogan m pratt video, you can try to understand it i wont comment it -jr
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

    public void stopDrive() {
        m_frontLeft.stopMotor();
        m_backLeft.stopMotor();
        m_frontRight.stopMotor();
        m_backRight.stopMotor();
        telemetry.addData("x  ",0);
        telemetry.addData("y  ",0);
        telemetry.addData("rx ",0);
    }

    public void setV(boolean isVelocityControl) {
        k_vel = isVelocityControl;
        MotorInit.setV(m_frontLeft, isVelocityControl);
        MotorInit.setV(m_frontRight, isVelocityControl);
        MotorInit.setV(m_backLeft, isVelocityControl);
        MotorInit.setV(m_backRight, isVelocityControl);
    }

    public void setK() {
        MotorInit.setK(m_frontRight, k_coeff);
        MotorInit.setK(m_frontLeft, k_coeff);
        MotorInit.setK(m_backRight, k_coeff);
        MotorInit.setK(m_backLeft, k_coeff);
    }

    @Override
    public void periodic() {
        /* was used to compare the accuracy of encoder/non encoder drive
        if (setup) {

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
        }
        */
        telemetry.addData("encoder usage: ", k_vel);
        telemetry.update();
    }

}
