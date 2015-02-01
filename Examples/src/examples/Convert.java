package examples;

import jBittorrentAPI.utils.Utils;

/**
 * Add comment here
 * <p/>
 * jbittorrentapi-v1.0
 * Pakage examples
 * By @author Mmuzaf
 * Created at 10.07.14
 */
public class Convert {

    public static final int LENGTH = 0x00000001;

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    public static void main(final String[] args) {
        System.out.println("TEST");
        //byte [] x = hexStringToByteArray("0000001");
        byte [] x = Utils.intTo4ByteArray(LENGTH);
        for (byte aX : x) {
            System.out.println(aX);
        }
    }

}
