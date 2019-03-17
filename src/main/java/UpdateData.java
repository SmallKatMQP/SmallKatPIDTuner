import edu.wpi.SimplePacketComs.BytePacketType;
import edu.wpi.SimplePacketComs.PacketType;
import edu.wpi.SimplePacketComs.phy.HIDSimplePacketComs;

public class UpdateData extends HIDSimplePacketComs {
    private PacketType gamestate;
    private final byte[] status = new byte[60];
    private final byte[] data = new byte[20];

    public UpdateData(int vidIn, int pidIn, int ID) {
        super(vidIn, pidIn);
       gamestate = new BytePacketType(ID, 64);
        addPollingPacket(gamestate);
        gamestate.waitToSendMode();
        addEvent(gamestate.idOfCommand, new Runnable() {
            @Override
            public void run() {
                readBytes(gamestate.idOfCommand, getData());
                writeBytes(gamestate.idOfCommand, getStatus());
            }
        });
    }
    public byte[] getStatus() {
        return status;
    }
    public byte[] getData() {
        return data;
    }
}

