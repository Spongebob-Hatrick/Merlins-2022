package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "mikeDyson12_09_21TO")
public class mikeDyson12_09_21TO extends LinearOpMode {

    private DcMotorEx frontLeft;
    private DcMotorEx backLeft;
    private DcMotorEx frontRight;
    private DcMotorEx backRight;
    private DcMotorEx intakeMotor;
    private DcMotorEx ladder;
    private DcMotorEx carousel1;
    private DcMotorEx carousel2;
    private Servo basket;

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

        ladder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        carousel1 = (DcMotorEx) hardwareMap.get(DcMotor.class, "carousel1");
        carousel2 = (DcMotorEx) hardwareMap.get(DcMotor.class, "carousel2");
        basket = hardwareMap.get(Servo.class, "basket");


        waitForStart();

        telemetry.addData("Encoders", ladder.getCurrentPosition());
        telemetry.update();

        while (opModeIsActive()) {

            if (gamepad1.y) {
                backLeft.setVelocity(100);
                frontLeft.setVelocity(100);
                frontRight.setVelocity(100);
                backRight.setVelocity(100);
            }


            if (gamepad2.y) {
                //ladder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                ladder.setTargetPosition(2700);
                ladder.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ladder.setVelocity(300);
                telemetry.addData("Encoders", ladder.getCurrentPosition());
                telemetry.update();

                while (ladder.isBusy()) ;
                ladder.setPower(0);
                telemetry.addData("Encoders", ladder.getCurrentPosition());
                telemetry.update();
            }//high level


            if (gamepad2.x) {
                //ladder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                ladder.setTargetPosition(2000);
                ladder.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ladder.setVelocity(300);
                telemetry.addData("Encoders", ladder.getCurrentPosition());
                telemetry.update();

                while (ladder.isBusy());
                ladder.setPower(0);
                telemetry.addData("Encoders", ladder.getCurrentPosition());
                telemetry.update();
            }// medium level


            if (gamepad2.a) {
                // ladder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                ladder.setTargetPosition(1350);
                ladder.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ladder.setVelocity(300);
                telemetry.addData("Encoders", ladder.getCurrentPosition());
                telemetry.update();

                while (ladder.isBusy()) ;
                ladder.setPower(0);
                telemetry.addData("Encoders", ladder.getCurrentPosition());
                telemetry.update();
            }// low level


            if (gamepad2.b){
                ladder.setTargetPosition(0);
                ladder.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                ladder.setVelocity(300);
                telemetry.addData("Encoders", ladder.getCurrentPosition());
                telemetry.update();

                while (ladder.isBusy()) ;
                ladder.setPower(0);
                telemetry.addData("Encoders", ladder.getCurrentPosition());
                telemetry.update();
            }//return to 0


            if (gamepad2.left_bumper) {
                basket.setDirection(Servo.Direction.REVERSE);
                basket.setPosition(1);
            }
            else if (gamepad2.right_bumper) {
                basket.setDirection(Servo.Direction.FORWARD);
                basket.setPosition(0.5);
            }//basket


            if (gamepad2.right_trigger > 0.01 && gamepad2.right_trigger < 0.19) {
                carousel1.setVelocity(100);
                carousel2.setVelocity(-100);
            }
            else if (gamepad2.right_trigger > 0.2 && gamepad2.right_trigger < 0.39) {
                carousel1.setVelocity(500);
                carousel2.setVelocity(-500);
            }
            else if (gamepad2.right_trigger > 0.4 && gamepad2.right_trigger < 0.59) {
                carousel1.setVelocity(1000);
                carousel2.setVelocity(-1000);
            }
            else if (gamepad2.right_trigger > 0.6 && gamepad2.right_trigger < 0.79) {
                carousel1.setVelocity(1500);
                carousel2.setVelocity(-1500);
            }
            else if (gamepad2.right_trigger > 0.8 && gamepad2.right_trigger < 0.89) {
                carousel1.setVelocity(2000);
                carousel2.setVelocity(-2000);
            }
            else if (gamepad2.right_trigger>0.9) {
                carousel1.setVelocity(5000);
                carousel2.setVelocity(-5000);
            }
            else {
                carousel1.setPower(0);
                carousel2.setPower(0);
            }


            if (gamepad2.dpad_up) {
                intakeMotor.setVelocity(5000);
            }
            else {
                intakeMotor.setPower(0);
            }


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

        }
    }
}

