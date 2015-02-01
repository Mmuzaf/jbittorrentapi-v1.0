package jBittorrentAPI.peerwire;

import jBittorrentAPI.utils.Utils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.BitSet;

/**
 * Add comment here
 * <p/>
 * jbittorrentapi-v1.0
 * Pakage jBittorrentAPI.peerwire
 * By @author Mmuzaf
 * Created at 11.07.14
 */
public class BitFieldMessage extends AbstractPeerProtocolMessage {
    public static final int MESSAGE_ID = 0x05;
    public BitSet bitField;

    public BitFieldMessage(BitSet bitField) {
        super(MESSAGE_ID);
        this.bitField = bitField;
    }

    @Override
    public void write(OutputStream o) throws IOException {
        o.write(
                ArrayUtils.addAll(
                        ArrayUtils.addAll(
                                Utils.intTo4ByteArray(1 + bitField.size()),
                                Utils.intTo4ByteArray(MESSAGE_ID)
                        ),
                        bitField.toByteArray()
                )
        );
    }

    @Override
    public void read(InputStream i) {
    }

    @Override
    public String toString() {
        return "BIT FIELD MESSAGE [bitField=" + bitField + "]";
    }
}
