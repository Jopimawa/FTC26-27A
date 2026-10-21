package org.firstinspires.ftc.teamcode.subsystem;


import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.helper.MotorInit;

// every moving part related to cell scoring -jr
@Configurable
public class ThrowerSubsystem extends SubsystemBase {
    private static Motor m_flywheel;
    private static boolean k_flywheelInv = false;
    private static double[] k_flywheelCoeff = new double[] {0.5, 0, 0.022, 0.02, 1.2};

    public ThrowerSubsystem(HardwareMap hardwareMap) {
        m_flywheel =  new Motor(hardwareMap,"flywheel");
        MotorInit.setupMotor(m_flywheel, k_flywheelInv,false,true, k_flywheelCoeff);
    }

   public void setFlywheel(double power) {
        MotorInit.setK(m_flywheel, k_flywheelCoeff);
        m_flywheel.set(power);
    }
    public void stopFlywheel() {
        m_flywheel.stopMotor();
    }


}
