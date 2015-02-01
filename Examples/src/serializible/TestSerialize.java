package serializible;

import java.io.*;

/**
 * Add comment here
 * <p/>
 * jbittorrentapi-v1.0
 * Pakage serializible
 * By @author Mmuzaf
 * Created at 14.08.14
 */
public class TestSerialize {
    public static void main(String[] args) {
        DataObject d = new DataObject();
        File file = new File("output.bin");


        d.setMyData("hahahaha");
        try {
            FileOutputStream fo = new FileOutputStream(file);
            ObjectOutputStream os = new ObjectOutputStream(fo);

            os.writeObject(d);
            os.flush();
            os.close();


           FileInputStream fi = new FileInputStream(file);
           ObjectInputStream oi = new ObjectInputStream(fi);
            try {
                DataObject doread = (DataObject) oi.readObject();
                System.out.println(doread.toString());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            oi.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
