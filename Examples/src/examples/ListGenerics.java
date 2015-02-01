package examples;

/**
 * Add comment here
 * <p/>
 * jbittorrentapi-v1.0
 * Pakage examples
 * By @author Mmuzafarov
 * Created at 26.06.14
 */
public class ListGenerics {
    public static <T> void out(T key) {
        Class type = key.getClass();
        if (key instanceof Integer) {
            int key2 = (Integer) key;
        }
        System.out.println(key.getClass().getName());
    }
}
