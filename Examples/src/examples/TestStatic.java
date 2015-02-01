package examples;

/**
 * Add comment here
 * <p/>
 * jbittorrentapi-v1.0
 * Pakage examples
 * By @author Mmuzaf
 * Created at 11.08.14
 */
public class TestStatic {
    public static int a = 4;
    public int b = 7;



    static {
        a++;
    }

    public TestStatic() {
        a++;
        b++;
    }

    public void sum() {
        b = b + a + 2;
    }


    public static void main(String[] args) {
        TestStatic ts1 = new TestStatic();
        TestStatic ts2 = new TestStatic();

        ts2.sum();
        System.out.println(ts2.b);
        System.out.println(ts2.a);
        System.out.println(ts1.b);
    }
}
