package generics;

import java.util.ArrayList;
import java.util.List;

/**
 * Add comment here
 * <p/>
 * jbittorrentapi-v1.0
 * Pakage generics
 * By @author Mmuzaf
 * Created at 14.08.14
 */
public class TestGenerics {
    public static void main(String[] agrs) {
        List rawList = new ArrayList();
        List<String> list = new ArrayList<String>();

        rawList = list;
        rawList.add(8);

        String s = list.get(0);

    }
}
