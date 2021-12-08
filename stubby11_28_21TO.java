package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "stubby11_28_21TO")
public class stubby11_28_21TO extends LinearOpMode {

    private DcMotorEx frontLeft;
    private DcMotorEx backLeft;
    private DcMotorEx frontRight;
    private DcMotorEx backRight;
    private DcMotorEx intakeMotor;
    private DcMotorEx ladder;
    private DcMotorEx carousel;
    private Servo basket;
    double power = 0.05;

    @Override
    public void runOpMode() {
        frontLeft = (DcMotorEx) hardwareMap.get(DcMotor.class, "frontLeft");
        backLeft = (DcMotorEx) hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = (DcMotorEx) hardwareMap.get(DcMotor.class, "frontRight");
        backRight = (DcMotorEx) hardwareMap.get(DcMotor.class, "backRight");
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        intakeMotor = (DcMotorEx) hardwareMap.get(DcMotor.class, "intakeMotor");
        ladder = (DcMotorEx) hardwareMap.get(DcMotor.class, "ladder");
        carousel = (DcMotorEx) hardwareMap.get(DcMotor.class, "carousel");
        basket = hardwareMap.get(Servo.class, "basket");



        waitForStart();

        ladder.setTargetPosition(5000);
        ladder.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        ladder.setVelocity(1000);
        telemetry.addData("Encoders", ladder.getCurrentPosition());
        telemetry.update();

        while (opModeIsActive()) {
            double powerStrafe = gamepad1.left_stick_x;
            if (Math.abs(powerStrafe) < 0.05) powerStrafe = 0;
            double powerForward = -gamepad1.left_stick_y;
            if (Math.abs(powerForward) < 0.05) powerForward = 0;
            double powerTurn = -gamepad1.right_stick_x;
            if (Math.abs(powerTurn) < 0.05) powerTurn = 0;
            double p1 = -powerStrafe + powerForward - powerTurn;
            double p2 = powerStrafe + powerForward - powerTurn;
            double p3 = -powerStrafe + powerForward + powerTurn;
            double p4 = powerStrafe + powerForward + powerTurn;
            double max = Math.max(1.0, Math.abs(p1));
            max = Math.max(max, Math.abs(p2));
            max = Math.max(max, Math.abs(p3));
            max = Math.max(max, Math.abs(p4));
            p1 /= max;
            p2 /= max;
            p3 /= max;
            p4 /= max;
            backLeft.setPower(p1);
            frontLeft.setPower(p2);
            frontRight.setPower(p3);
            backRight.setPower(p4);
            telemetry.update();


            if (gamepad1.y) {
                backLeft.setVelocity(p1 * 0);
                frontLeft.setVelocity(p2 * 0);
                frontRight.setVelocity(p3 * 0);
                backRight.setVelocity(p4 * 0);
            }


            if (gamepad2.a) {
                ladder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                ladder.setTargetPosition(5000);
                ladder.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ladder.setVelocity(1000);
                while (ladder.isBusy());
                ladder.setPower(0);
                telemetry.addData("Encoders", ladder.getCurrentPosition());
                telemetry.update();

            } else{
                intakeMotor.setVelocity(0);

            }


            if (gamepad2.b) {
                ladder.setVelocity(100);

            } else {
                ladder.setVelocity(0);

            }


            if (gamepad2.x) {
                (ladder).setVelocity(5000);
                telemetry.addData("Encoders", ladder.getCurrentPosition());
                telemetry.update();
                                while (ladder.getCurrentPosition() > 1600){
                    (ladder).setVelocity(600);

                }
            } else {
                (ladder).setVelocity(0);
            }


            if (gamepad2.left_bumper) {
                basket.setDirection(Servo.Direction.REVERSE);
                basket.setPosition(1);
            }
            if (gamepad2.right_bumper) {
                basket.setDirection(Servo.Direction.FORWARD);
                basket.setPosition(0.5);
            }
        }
    }
}
