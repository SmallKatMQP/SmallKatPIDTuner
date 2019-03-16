import edu.wpi.SimplePacketComs.phy.HIDSimplePacketComs;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import edu.wpi.SimplePacketComs.*;

import java.awt.*;

public class PIDTuner extends Application  {
    private static HIDSimplePacketComs MotherBoard;
    private static PacketType kp = new edu.wpi.SimplePacketComs.BytePacketType(1963, 64);
    private static PacketType ki = new edu.wpi.SimplePacketComs.BytePacketType(1964, 64);
    private static PacketType kd = new edu.wpi.SimplePacketComs.BytePacketType(1965, 64);
    private static PacketType grav = new edu.wpi.SimplePacketComs.BytePacketType(1966, 64);
    private static PacketType cor = new edu.wpi.SimplePacketComs.BytePacketType(1967, 64);
    private static final byte[] data = new byte[64];
    private static final byte[] dataUp = new byte[64];

    @FXML
    Button UpdateKP;
    @FXML
    Button UpdateKI;
    @FXML
    Button UpdateKD;
    @FXML
    Button UpdateGrav;
    @FXML
    Button UpdateCor;

    @FXML
    ToggleButton ShoulderPanBtn;
    @FXML
    ToggleButton ShoulderTiltBtn;
    @FXML
    ToggleButton ElbowBtn;
    @FXML
    ToggleButton AnkleBtn;
    @FXML
    ToggleButton FR;
    @FXML
    ToggleButton FL;
    @FXML
    ToggleButton BR;
    @FXML
    ToggleButton BL;

    @FXML
    javafx.scene.control.TextField KP;
    @FXML
    javafx.scene.control.TextField KI;
    @FXML
    javafx.scene.control.TextField KD;
    @FXML
    javafx.scene.control.TextField GRAV;
    @FXML
    javafx.scene.control.TextField COR;



    public static void main(String[] args)throws InterruptedException  {
        MotherBoard = new HIDSimplePacketComs(0x3742,0x5751);
        MotherBoard.connect();
        MotherBoard.addPollingPacket(kp);
        MotherBoard.addPollingPacket(ki);
        MotherBoard.addPollingPacket(kd);
        MotherBoard.addPollingPacket(grav);
        MotherBoard.addPollingPacket(cor);
        kp.waitToSendMode();
        ki.waitToSendMode();
        kd.waitToSendMode();
        grav.waitToSendMode();
        cor.waitToSendMode();

        MotherBoard.addEvent(kp.idOfCommand, new Runnable() {
            @Override
            public void run() {
                MotherBoard.readBytes(kp.idOfCommand, data);
                MotherBoard.writeBytes(kp.idOfCommand, dataUp);
            }
        });
        MotherBoard.addEvent(ki.idOfCommand, new Runnable() {
            @Override
            public void run() {
                MotherBoard.readBytes(ki.idOfCommand, data);
                MotherBoard.writeBytes(ki.idOfCommand, dataUp);
            }
        });
        MotherBoard.addEvent(kd.idOfCommand, new Runnable() {
            @Override
            public void run() {
                MotherBoard.readBytes(kd.idOfCommand, data);
                MotherBoard.writeBytes(kd.idOfCommand, dataUp);
            }
        });
        MotherBoard.addEvent(grav.idOfCommand, new Runnable() {
            @Override
            public void run() {
                MotherBoard.readBytes(grav.idOfCommand, data);
                MotherBoard.writeBytes(grav.idOfCommand, dataUp);
            }
        });
        MotherBoard.addEvent(cor.idOfCommand, new Runnable() {
            @Override
            public void run() {
                MotherBoard.readBytes(cor.idOfCommand, data);
                MotherBoard.writeBytes(cor.idOfCommand, dataUp);
            }
        });


        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("PIDTuner.fxml"));
        primaryStage.setTitle("SmallKat PID Tuning APP");
        primaryStage.setScene(new Scene(root, 1400, 900));
        primaryStage.show();
    }

    public void SetKP(){
        float data = 0;
        data = (float)(Integer.parseInt(KP.getCharacters().toString())/100);
        System.out.println(data);
        //Send Downstream Here
    }
    public void SetKI(){
        float data = 0;
        data = (float)(Integer.parseInt(KI.getCharacters().toString())/100);

        //Send Downstream Here

    }
    public void SetKD(){
        float data = 0;
        data = (float)(Integer.parseInt(KD.getCharacters().toString())/100);
        //Send Downstream Here

    }
    public void SetGrav(){
        float data = 0;
        data = (float)(Integer.parseInt(GRAV.getCharacters().toString())/1000);
        //Send Downstream Here

    }
    public void SetCor(){
        float data = 0;
        data = (float)(Integer.parseInt(COR.getCharacters().toString())/1000);
        //Send Downstream Here

    }
    public void SPCheck(){
        if(ShoulderTiltBtn.isSelected()){
            ShoulderTiltBtn.setSelected(false);
        }
        if(ElbowBtn.isSelected()){
            ElbowBtn.setSelected(false);
        }
        if(AnkleBtn.isSelected()){
            AnkleBtn.setSelected(false);
        }
        //System.out.println("ShoulderPan");

    }
    public void STCheck(){
        if(ShoulderPanBtn.isSelected()){
            ShoulderPanBtn.setSelected(false);
        }
        if(ElbowBtn.isSelected()){
            ElbowBtn.setSelected(false);
        }
        if(AnkleBtn.isSelected()){
            AnkleBtn.setSelected(false);
        }
        //System.out.println("ShoulderTilt");


    }
    public void ElbowCheck(){
        if(ShoulderTiltBtn.isSelected()){
            ShoulderTiltBtn.setSelected(false);
        }
        if(ShoulderPanBtn.isSelected()){
            ShoulderPanBtn.setSelected(false);
        }
        if(AnkleBtn.isSelected()){
            AnkleBtn.setSelected(false);
        }
        //System.out.println("Elbow");


    }
    public void AnkleCheck(){
        if(ShoulderTiltBtn.isSelected()){
            ShoulderTiltBtn.setSelected(false);
        }
        if(ElbowBtn.isSelected()){
            ElbowBtn.setSelected(false);
        }
        if(ShoulderPanBtn.isSelected()){
            ShoulderPanBtn.setSelected(false);
        }
        //System.out.println("Ankle");

    }

    public void FRCheck(){
        if(FL.isSelected()){
            FL.setSelected(false);
        }
        if(BR.isSelected()){
            BR.setSelected(false);
        }
        if(BL.isSelected()){
            BL.setSelected(false);
        }

    }
    public void FLCheck(){
        if(FR.isSelected()){
            FR.setSelected(false);
        }
        if(BR.isSelected()){
            BR.setSelected(false);
        }
        if(BL.isSelected()){
            BL.setSelected(false);
        }
    }
    public void BRCheck(){
        if(FL.isSelected()){
            FL.setSelected(false);
        }
        if(FR.isSelected()){
            FR.setSelected(false);
        }
        if(BL.isSelected()){
            BL.setSelected(false);
        }

    }
    public void BLCheck(){
        if(FL.isSelected()){
            FL.setSelected(false);
        }
        if(BR.isSelected()){
            BR.setSelected(false);
        }
        if(FR.isSelected()){
            FR.setSelected(false);
        }
    }
    public int getLegBtnsState(){
        int state = 0;
        if(FR.isSelected()){
            state = 0;
        }else  if(FL.isSelected()){
            state = 4;
        }else  if(BR.isSelected()){
            state = 8;
        }else  if(BL.isSelected()){
            state = 12;
        }

        return state;

    }
    public int getJointBtnsState(){
        int state = 0;
        if(ShoulderPanBtn.isSelected()){
            state = 1;
        }else  if(ShoulderTiltBtn.isSelected()){
            state = 2;
        }else  if(ElbowBtn.isSelected()){
            state = 3;
        }else  if(AnkleBtn.isSelected()){
            state = 4;
        }

        return state;

    }
    public int getIndex(){
        return(getLegBtnsState()+getJointBtnsState());
    }
}
