package examples;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Add comment here
 * <p/>
 * jbittorrentapi-v1.0
 * Pakage examples
 * By @author Mmuzaf
 * Created at 13.08.14
 */
public class TreeMapComparator {

    public static void main(String[] args) {
        Set<Integer> set = new TreeSet<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });

        set.add(1);
        set.add(2);
        set.add(1);

        System.out.println(set);
    }
}
