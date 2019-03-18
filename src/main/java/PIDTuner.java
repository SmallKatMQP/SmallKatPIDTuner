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
    private static UpdateData Motherboard;


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
    Button UpdatePos;

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
    @FXML
    javafx.scene.control.TextField Position;



    public static void main(String[] args)throws InterruptedException  {

        //Configure DownStreamSending

        Motherboard = new UpdateData(0x3742,0x5751);
        Motherboard.connect();

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("PIDTuner.fxml"));
        primaryStage.setTitle("SmallKat PID Tuning APP");
        primaryStage.setScene(new Scene(root, 1400, 900));
        primaryStage.show();
    }


    //This is where all the values get set downstream
    public void SetKP(){
        int data = 0;
        int Type = 0;
        data = (int)(Float.parseFloat(KP.getCharacters().toString())*100);
        int index = getIndex();
        System.out.println(data+","+index);
        byte[] DownStream = numToBytes(data);
        Motherboard.getData(Type)[index*2] = DownStream[0];
        Motherboard.getData(Type)[(index*2)+1] = DownStream[1];

        //Send Downstream Here
    }
    public void SetKI(){
        int data = 0;
        int Type = 1;
        data = (int)(Float.parseFloat(KI.getCharacters().toString())*100);
        int index = getIndex();
        System.out.println(data+","+index);
        numToBytes(data);

        byte[] DownStream = numToBytes(data);
        Motherboard.getData(Type)[index*2] = DownStream[0];
        Motherboard.getData(Type)[(index*2)+1] = DownStream[1];
        //Send Downstream Here

    }
    public void SetKD(){
        int data = 0;
        int Type = 2;

        data = (int)(Float.parseFloat(KD.getCharacters().toString())*100);
        int index = getIndex();
        System.out.println(data+","+index);
        numToBytes(data);

        byte[] DownStream = numToBytes(data);
        Motherboard.getData(Type)[index*2] = DownStream[0];
        Motherboard.getData(Type)[(index*2)+1] = DownStream[1];
        //Send Downstream Here

    }
    public void SetGrav(){
        int data = 0;
        int Type = 3;

        data = (int)(Float.parseFloat(GRAV.getCharacters().toString())*1000);

        int index = getIndex();
        System.out.println(data+","+index);
        numToBytes(data);

        byte[] DownStream = numToBytes(data);
        Motherboard.getData(Type)[index*2] = DownStream[0];
        Motherboard.getData(Type)[(index*2)+1] = DownStream[1];
        //Send Downstream Here

    }
    public void SetCor(){
        int data = 0;
        int Type = 4;

        data = (int)(Float.parseFloat(COR.getCharacters().toString())*1000);
        int index = getIndex();
        System.out.println(data+","+index);
        numToBytes(data);

        byte[] DownStream = numToBytes(data);
        Motherboard.getData(Type)[index*2] = DownStream[0];
        Motherboard.getData(Type)[(index*2)+1] = DownStream[1];
        //Send Downstream Here

    }
    public void setPos(){
        int data = 0;
        int Type = 5;

        data = (int)(Float.parseFloat(Position.getCharacters().toString()));
        int index = getIndex();
        System.out.println(data+","+index);
        numToBytes(data);
        byte[] DownStream = numToBytes(data);
        Motherboard.getData(Type)[index*2] = DownStream[0];
        Motherboard.getData(Type)[(index*2)+1] = DownStream[1];

    }


    //Button State checing for overlap
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

    public byte[] numToBytes(int Data){
        byte[] shiftedData = new byte[2];
        shiftedData[0] = (byte)(Data>>8);
        shiftedData[1] = (byte)(Data);
        return shiftedData;
    }
}
