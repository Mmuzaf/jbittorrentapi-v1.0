package examples;

import jBittorrentAPI.Constants;
import jBittorrentAPI.bencode.BCoder;
import jBittorrentAPI.bencode.btypes.BMap;
import jBittorrentAPI.bencode.btypes.HashBMap;
import jBittorrentAPI.utils.IOManager;
import jBittorrentAPI.utils.TorrentBuilder;
import jBittorrentAPI.utils.Utils;

import java.io.File;

/**
 * Project Bencoding
 * Created by Francis on 26/03/14.
 */
public class Application {

    public static void main(final String[] args) {
        //final BElement[] elements3 = BReader.decode("i523e5:abcdel4:spam4:eggsed3:cow3:moo4:spam4:eggse");
        TorrentBuilder builder;
        //builder.construct(BCoder.decode(IOManager.readBytesFromFile(new File("C:/Temp/[rutracker.org].t3213608.torrent"))).getValue(0));
        builder = new TorrentBuilder((BMap) BCoder.decode(IOManager.readBytesFromFile(new File("C:/Temp/[rutracker.org].t3222256.torrent"))).get(0));

        //System.out.println(builder.getTorrent().toString());

        System.out.println(new String(Utils.byteArrayToURLString("\\x12\\x34\\x56\\x78\\x9a\\xbc\\xde\\xf1\\x23\\x45\\x67\\x89\\xab\\xcd\\xef\\x12\\x34\\x56\\x78\\x9a".getBytes())));
        //System.out.println(Arrays.toString(elements3));
        //System.out.println(Arrays.toString(el));
    }

}
