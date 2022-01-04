package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import java.util.Set;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name="testAuto", group="Auto")
public class testAuto extends LinearOpMode {

    private DcMotor frontLeft;
    private DcMotor backLeft;
    private DcMotor frontRight;
    private DcMotor backRight;

    @Override
    public void runOpMode() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);


        frontRight.setTargetPosition(500);
        // reset encoder count kept by right motor.
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // set right motor to run to target encoder position and stop with brakes on.
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // set left motor to run without regard to an encoder.
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.

        waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();

        // set motors to 25% power. Movement will start.
        frontLeft.setPower(0.25);
        backLeft.setPower(0.25);
        frontRight.setPower(0.25);
        backRight.setPower(0.25);

        // wait while opmode is active and left motor is busy running to position.
        while (opModeIsActive() && frontRight.isBusy())
        {
            telemetry.addData("encoder-fwd", frontRight.getCurrentPosition() + "  busy=" + frontRight.isBusy());
            telemetry.update();
            idle();
        }
        // set motor power to zero to turn off motors. The motors stop on their own but
        // power is still applied so we turn off the power.
        frontLeft.setPower(0.0);
        frontRight.setPower(0.0);
        backLeft.setPower(0);
        backRight.setPower(0);

        // wait 17 sec to you can observe the final encoder position.
        resetStartTime();
        {
            telemetry.addData("encoder-fwd-end", frontRight.getCurrentPosition() + "  busy=" + frontLeft.isBusy());
            telemetry.update();
            idle();
        }



        sleep(2500);
        while (opModeIsActive() && getRuntime() < 5)
            // set position for back up to starting point. In this example instead of
            // having the motor monitor the encoder we will monitor the encoder ourselves.

            frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        frontLeft.setTargetPosition(500);

        frontRight.setPower(0.25);
        frontLeft.setPower(0.25);
        backLeft.setPower(0.25);
        backRight.setPower(0.25);

        while (opModeIsActive() && frontRight.getCurrentPosition() < frontRight.getTargetPosition())
        {
            telemetry.addData("encoder-back", frontRight.getCurrentPosition());
            telemetry.update();
            idle();
        }

        // set motor power to zero to stop motors.
        frontRight.setPower(0.0);
        frontLeft.setPower(0.0);
        backLeft.setPower(0);
        backRight.setPower(0);
        resetStartTime();

        /*while (opModeIsActive() && getRuntime() < 5)
        {
            telemetry.addData("encoder-back-end", Frontright.getCurrentPosition());
            telemetry.update();
            idle();
     }*/

    }
}