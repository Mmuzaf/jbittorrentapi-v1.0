package jBittorrentAPI.peerwire;

import java.io.DataInputStream;
import java.io.InputStream;

/**
 * Add comment here
 * <p/>
 * jbittorrentapi-v1.0
 * Pakage jBittorrentAPI.peerwire
 * By @author Mmuzaf
 * Created at 14.07.14
 */
public class PeerMessageDecoder {
    private boolean handshaked = false;
    private DataInputStream stream;

    public PeerMessageDecoder() {

    }

    public PeerProtocolMessage decode(InputStream i) {
        if (!handshaked) {
            final HandShakeMessage message = new HandShakeMessage();
            message.read(i);
            return message;
        } else {
            if(buffer.readableBytes() == 0)
                return new KeepAliveMessage();

            final byte opcode = buffer.readByte();
            final PeerWireMessage message;
            switch (opcode) {
                case CancelMessage.MESSAGE_ID:
                    message = new CancelMessage();
                    break;
                case BitFieldMessage.MESSAGE_ID:
                    message = new BitFieldMessage();
                    break;
                case ChokeMessage.MESSAGE_ID:
                    message = new ChokeMessage();
                    break;
                case HaveMessage.MESSAGE_ID:
                    message = new HaveMessage();
                    break;
                case InterestedMessage.MESSAGE_ID:
                    message = new InterestedMessage();
                    break;
                case NotInterestedMessage.MESSAGE_ID:
                    message = new NotInterestedMessage();
                    break;
                case BlockMessage.MESSAGE_ID:
                    message = new BlockMessage();
                    break;
                case PortMessage.MESSAGE_ID:
                    message = new PortMessage();
                    break;
                case RequestMessage.MESSAGE_ID:
                    message = new RequestMessage();
                    break;
                case UnchokeMessage.MESSAGE_ID:
                    message = new UnchokeMessage();
                    break;
                default:
                    return null;
            }
            message.read(buffer);
            return message;
        }
        return null;
    }
}
