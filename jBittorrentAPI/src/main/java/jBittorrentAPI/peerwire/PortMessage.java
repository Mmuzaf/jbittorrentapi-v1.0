package jBittorrentAPI.peerwire;

import jBittorrentAPI.utils.Utils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Add comment here
 * <p/>
 * jbittorrentapi-v1.0
 * Pakage jBittorrentAPI.peerwire
 * By @author Mmuzaf
 * Created at 13.07.14
 */
public class PortMessage extends AbstractPeerProtocolMessage{
    public static final int MESSAGE_ID = 0x09;
    public static final int LENGTH = 0x00000003;
    public int port;

    public PortMessage(int port) {
        super(MESSAGE_ID);
        this.port = port;
    }

    @Override
    public void write(OutputStream o) throws IOException {
        o.write(
                Utils.merge(
                        Utils.intTo4ByteArray(LENGTH),
                        Utils.intTo4ByteArray(MESSAGE_ID),
                        Utils.intTo4ByteArray(port)
                )
        );
    }

    @Override
    public void read(InputStream i) {
    }

    @Override
    public String toString() {
        return "PORT BLOCK MESSAGE [port=" + port + "]";
    }
}
