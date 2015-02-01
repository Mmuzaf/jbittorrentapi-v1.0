package serializible;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Add comment here
 * <p/>
 * jbittorrentapi-v1.0
 * Pakage serializible
 * By @author Mmuzaf
 * Created at 14.08.14
 */
public class CustomObject implements Externalizable{
    transient boolean b;

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {

    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }
}
