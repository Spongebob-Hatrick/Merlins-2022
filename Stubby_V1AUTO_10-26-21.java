package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous (group= "Auto", name = "Autonomous3 (Blocks to Java)")
public class Autonomous3 extends LinearOpMode{
  private DcMotor Frontleft;
  private DcMotor Backleft;
  private DcMotor Frontright;
  private DcMotor Backright;
  private DcMotor Intakewheels;
  private DcMotor Transfer;
  private DcMotorEx Shooterwheel;
  private DcMotor Arm;
  private Servo Kicker;
  private Servo Clamp;
  
  public void runOpMode(){
     Frontleft = hardwareMap.get(DcMotor.class, "Front left");
    Backleft = hardwareMap.get(DcMotor.class, "Back left");
    Frontright = hardwareMap.get(DcMotor.class, "Front right");
    Backright = hardwareMap.get(DcMotor.class, "Back right");
    Frontleft.setDirection(DcMotorSimple.Direction.REVERSE);
    Backleft.setDirection(DcMotorSimple.Direction.REVERSE);
    Intakewheels = hardwareMap.get(DcMotor.class, "Intake wheels");
    Transfer = hardwareMap.get(DcMotor.class, "Transfer ");
    Shooterwheel = (DcMotorEx)hardwareMap.get(DcMotor.class, "Shooter wheel");
    Arm = hardwareMap.get(DcMotor.class, "Arm");
    Kicker = hardwareMap.get(Servo.class,"Kicker");
    Clamp = hardwareMap.get(Servo.class, "Clamp");
    
    waitForStart();
   
    if (opModeIsActive()){
      Clamp.setDirection(Servo.Direction.REVERSE);
      Clamp.setPosition(1); 
      
   MoveForward();
    sleep(4375);
    StopBot();
    
     Frontleft.setPower(-0.25);
    Frontright.setPower(0.25);
    Backleft.setPower(0.25);
    Backright.setPower(-0.25);
    sleep(2000);
    StopBot();
    
    Frontright.setPower(0.25);
    sleep(400);
    StopBot();
    

       ((DcMotorEx) Shooterwheel).setVelocity(1600);
      while (Shooterwheel.isMotorEnabled()){
          sleep(1000);
          ShootDisc();
          ShootDisc();
          ShootDisc();
          
          break;
      } Shooterwheel.setVelocity(0);
         
      MoveForward();
      sleep(1000);
      StopBot();
    
       sleep(500);
        
    Frontleft.setPower(-0.25);
    Frontright.setPower(0.25);
    Backleft.setPower(0.25);
    Backright.setPower(-0.25);
    sleep(2000);
    StopBot();
        
    Frontright.setPower(0.3);
    sleep(450);
    
    sleep(500);
        
    MoveForward();
    sleep(2200); 
    StopBot();
      
    Arm();
    sleep(500);
    StopBot();
    
      
    Clamp();
    sleep(1500);
    StopBot();
    
    Arm.setPower(-0.3);
    sleep(1000);
    Arm.setPower(0);
    StopBot();
    
    sleep(500);
    
    MoveBackward();
    sleep(2700);
    StopBot();
    }
  }
 public void ShootDisc(){
   Kicker.setDirection(Servo.Direction.REVERSE);
          Kicker.setPosition(1);
          sleep(750);
          
          Kicker.setDirection(Servo.Direction.FORWARD);
          Kicker.setPosition(0.5);
          sleep(750);
 }
 public void Arm(){
   
   Arm.setPower(0.3);
      sleep(800);
  Arm.setPower(0);
      StopBot();
      
    }
  
  public void Clamp(){
  Clamp.setDirection(Servo.Direction.FORWARD);
      Clamp.setPosition(0.5);
      sleep(1000);
  }


 public void setPower(float powerStrafe, float powerForward, float powerTurn){
   
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
                Backleft.setPower(p1);
                Frontleft.setPower(p2);
                Frontright.setPower(p3);
                Backright.setPower(p4);
 }
 public void MoveForward(){
    setPower(0,0.25f,0);
 }
  public void MoveBackward(){
    setPower(0,-0.25f,0);
  }
  public void StopBot(){
    setPower(0,0,0);
  }
}


