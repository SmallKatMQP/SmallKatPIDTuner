import edu.wpi.SimplePacketComs.BytePacketType;
import edu.wpi.SimplePacketComs.PacketType;
import edu.wpi.SimplePacketComs.phy.HIDSimplePacketComs;

public class UpdateData extends HIDSimplePacketComs {
    private PacketType Kp = new BytePacketType(1804, 64);
    private PacketType Ki = new BytePacketType(1805, 64);
    private PacketType Kd = new BytePacketType(1806, 64);
    private PacketType grav = new BytePacketType(1807, 64);
    private PacketType cor = new BytePacketType(1808, 64);
    private PacketType pos = new BytePacketType(1962, 64);

    private final byte[] dataKp = new byte[64];
    private final byte[] dataUpKp = new byte[64];
    private final byte[] dataKi = new byte[64];
    private final byte[] dataUpKi = new byte[64];
    private final byte[] dataKd = new byte[64];
    private final byte[] dataUpKd = new byte[64];
    private final byte[] dataGrav = new byte[64];
    private final byte[] dataUpGrav = new byte[64];
    private final byte[] dataCor = new byte[64];
    private final byte[] dataUpCor = new byte[64];
    private final byte[] dataPos = new byte[64];
    private final byte[] dataUpPos = new byte[64];

    public UpdateData(int vidIn, int pidIn) {
        super(vidIn, pidIn);
        addPollingPacket(Kp);
        addPollingPacket(Ki);
        addPollingPacket(Kd);
        addPollingPacket(grav);
        addPollingPacket(cor);
        addPollingPacket(pos);


//        Kp.waitToSendMode();
//        Ki.waitToSendMode();
//        Kd.waitToSendMode();
//        grav.waitToSendMode();
//        cor.waitToSendMode();
//        pos.waitToSendMode();


        addEvent(Kp.idOfCommand, new Runnable() {
            @Override
            public void run() {
                readBytes(Kp.idOfCommand, getData(0));
                writeBytes(Kp.idOfCommand, getDataUp(0));
            }
        });
        addEvent(Ki.idOfCommand, new Runnable() {
            @Override
            public void run() {
                readBytes(Kp.idOfCommand, getData(1));
                writeBytes(Kp.idOfCommand, getDataUp(1));
            }
        });
        addEvent(Kd.idOfCommand, new Runnable() {
            @Override
            public void run() {
                readBytes(Kp.idOfCommand, getData(2));
                writeBytes(Kp.idOfCommand, getDataUp(2));
            }
        });
        addEvent(grav.idOfCommand, new Runnable() {
            @Override
            public void run() {
                readBytes(Kp.idOfCommand, getData(3));
                writeBytes(Kp.idOfCommand, getDataUp(3));
            }
        });
        addEvent(cor.idOfCommand, new Runnable() {
            @Override
            public void run() {
                readBytes(Kp.idOfCommand, getData(4));
                writeBytes(Kp.idOfCommand, getDataUp(4));
            }
        });
        addEvent(pos.idOfCommand, new Runnable() {
            @Override
            public void run() {
                readBytes(Kp.idOfCommand, getData(5));
                writeBytes(Kp.idOfCommand, getDataUp(5));
            }
        });
    }
    public byte[] getData(int Type) {
        if(Type == 0)
            return dataKp;
        else if (Type == 1)
            return dataKi;
        else if (Type == 2)
            return dataKd;
        else if (Type == 3)
            return dataGrav;
        else if (Type == 4)
            return dataCor;
        else
            return dataPos;

    }
    public byte[] getDataUp(int Type) {
        if(Type == 0)
            return dataUpKp;
        else if (Type == 1)
            return dataUpKi;
        else if (Type == 2)
            return dataUpKd;
        else if (Type == 3)
            return dataUpGrav;
        else if (Type == 4)
            return dataUpCor;
        else
            return dataUpPos;
    }
}

