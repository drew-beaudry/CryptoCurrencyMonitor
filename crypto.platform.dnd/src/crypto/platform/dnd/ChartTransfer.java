package crypto.platform.dnd;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;
import org.eclipse.swt.dnd.ByteArrayTransfer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.TransferData;

import crypto.model.Chart;

/** Serializes and Deserializes Chart objects for use in Drag n Drop*/
public class ChartTransfer extends ByteArrayTransfer {
  private static final Logger log = Logger.getLogger(ChartTransfer.class);
  private static final String MYTYPENAME = "Chart";

  private static final int MYTYPEID = registerType(MYTYPENAME);

  private static ChartTransfer _instance = new ChartTransfer();

  public static ChartTransfer getInstance() {
    return _instance;
  }

  public void javaToNative(Object object, TransferData transferData) {
    if (!checkObject(object) || !isSupportedType(transferData)) {
      log.error("ERROR_INVALID_DATA");
      DND.error(DND.ERROR_INVALID_DATA);
    }
    try {
      super.javaToNative(toByteArray(object), transferData);
    } catch (IOException e) {
      log.error("IOEXCEPTION");
    }
  }

  public static byte[] toByteArray(Object obj) throws IOException {
    byte[] bytes = null;
    ByteArrayOutputStream bos = null;
    ObjectOutputStream oos = null;
    try {
      bos = new ByteArrayOutputStream();
      oos = new ObjectOutputStream(bos);
      oos.writeObject(obj);
      oos.flush();
      bytes = bos.toByteArray();
    } catch (IOException e) {
      log.info("IOEXCEPTION");
      e.printStackTrace();
    } finally {
      if (oos != null) {
        oos.close();
      }
      if (bos != null) {
        bos.close();
      }
    }
    return bytes;
  }

  public Object nativeToJava(TransferData transferData) {
    if (isSupportedType(transferData)) {
      byte[] buffer = (byte[]) super.nativeToJava(transferData);
      if (buffer == null) return null;
      try {
        return toObject(buffer);
      } catch (ClassNotFoundException e) {
        log.info("CLASSNOTFOUNDEXCEPTION");
      } catch (IOException e) {
        log.info("IOEXCEPTION");
      }
    }
    return null;
  }

  public static Object toObject(byte[] bytes) throws IOException, ClassNotFoundException {
    Object obj = null;
    ByteArrayInputStream bis = null;
    ObjectInputStream ois = null;
    try {
      bis = new ByteArrayInputStream(bytes);
      ois = new ObjectInputStream(bis);
      obj = ois.readObject();
    } finally {
      if (bis != null) {
        bis.close();
      }
      if (ois != null) {
        ois.close();
      }
    }
    return obj;
  }

  protected String[] getTypeNames() {
    return new String[] {MYTYPENAME};
  }

  protected int[] getTypeIds() {
    return new int[] {MYTYPEID};
  }

  boolean checkObject(Object object) {
    if (object == null || !(object instanceof Chart)) {
      return false;
    } else return true;
  }

  protected boolean validate(Object object) {
    return checkObject(object);
  }
}
