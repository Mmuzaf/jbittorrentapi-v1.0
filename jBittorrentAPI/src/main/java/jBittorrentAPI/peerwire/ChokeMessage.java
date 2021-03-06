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
 * Created at 10.07.14
 */
public class ChokeMessage extends AbstractPeerProtocolMessage {
    public static final int MESSAGE_ID = 0x00;
    public static final int LENGTH = 0x00000001;

    public ChokeMessage() {
        super(MESSAGE_ID);
    }

    @Override
    public void write(OutputStream o) throws IOException {
        o.write(ArrayUtils.addAll(Utils.intTo4ByteArray(LENGTH),
                Utils.intTo4ByteArray(MESSAGE_ID)));
    }

    @Override
    public void read(InputStream i) {
    }

    @Override
    public String toString() {
        return "CHOKE MESSAGE []";
    }
}
