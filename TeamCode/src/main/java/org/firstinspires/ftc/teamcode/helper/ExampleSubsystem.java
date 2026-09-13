package org.firstinspires.ftc.teamcode.helper;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.CRServo;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ExampleSubsystem extends SubsystemBase {
    /*
    foo bar and buzz are common filler names in programming
    ### FORMATTING VARIABLES ###
    m_x is the naming convention for motor variables
    s_x is the naming convention for servo variables
    k_x is the naming convention for init variables (inverse, PIDF, brake constant) IM FINE WITH INLINE
    when you type a variable name and have multiple words do llama case:
    firstSecond
    notice how second word is capitalized, first is not.

    ### FORMATTING METHODS ###
    setX  is the naming convention for moving a motor/servo
    stopX is the naming convention for moving a motor/continuous servo
    getX  is the naming convention for fetching a variable for a command
    you may have other methods for updating values, usually updateX but name accordingly (see DriveSubsystem for examples)

    end ur comments with a name that others would understand
     -jr
    */
    private final Motor m_foo;
    private final ServoEx s_bar;
    private final CRServo s_buzz;
    private final boolean k_fooInv = false;
    private final Telemetry telemetry;

    public ExampleSubsystem(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        m_foo =   new Motor(hardwareMap, "Motor");
        m_foo.setInverted(k_fooInv);

        s_bar =   new ServoEx(hardwareMap, "Servo");

        s_buzz =  new CRServo(hardwareMap, "Continuous Servo");

    }
    public void setFoo(double power) {
        m_foo.set(power);
    }
    public void stopFoo() {
        m_foo.stopMotor();
    }
    public void setBar(double position) {
        s_bar.set(position);
    }
    public double getBar() {
        return s_bar.getRawPosition();
    }

    // notice how continuous servos are almost equivalent to motors. only difference is initialization (no encoders/brake init) -jr
    public void setBuzz(double power) {
        s_buzz.set(power);
    }
    public void stopBuzz() {
       s_buzz.stop();
       // also valid to use the motor stop -jr
       //s_buzz.stopMotor();
    }

    @Override
    public void periodic() {
        // periodic runs every cycle, which allows you to put telemetry here to see on the driver hub -jr
        telemetry.addData("foo  ", m_foo.getCPR());

        telemetry.addData("bar  ", s_bar.get());
        telemetry.addData("bar  ", s_bar.getRawPosition());

        telemetry.addData("buzz ", s_buzz.getCPR());
        double mathForTelemetry = 4*3+s_buzz.getCPR();
        telemetry.addData("math ", mathForTelemetry);
        // remember to update telemetry to send it to the screen
        telemetry.update();
    }

}
