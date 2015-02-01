package serializible;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Add comment here
 * <p/>
 * jbittorrentapi-v1.0
 * Pakage serializible
 * By @author Mmuzaf
 * Created at 14.08.14
 */
public class DataObject extends NonSerializible implements Serializable {
    int i = 5;
    String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
    transient String[] def;
    CustomObject co = new CustomObject();

    public DataObject() {
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeObject(getMyData());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        setMyData((String) in.readObject());
    }
}
