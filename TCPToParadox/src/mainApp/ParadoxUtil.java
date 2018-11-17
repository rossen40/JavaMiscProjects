package mainApp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParadoxUtil {

    private static Logger logger = LoggerFactory.getLogger(ParadoxUtil.class);

    public static byte calculateChecksum(byte[] payload) {
        int result = 0;
        for (byte everyByte : payload) {
            result += everyByte;
        }

        return (byte) (result % 256);
    }

    public static byte getBit(int value, int bitNumber) {
        return (byte) ((value >> bitNumber) & 1);
    }

    public static boolean isBitSet(int value, int bitNumber) {
        return ((value >> bitNumber) & 1) == 1;
    }

    public static void printPacket(String description, byte[] array) {
        logger.debug("Packet payload size: {}", array[1]);
    	printByteArray(description, array, array[1] + 16);
    }

    public static void printByteArray(String description, byte[] array, int length) {
    	   if (description != null && !description.isEmpty()) {
               logger.debug(description);
           }
    	   int countBytes = 0;
           String result = new String();
           for (int index = 0; index < length; index++) {
               countBytes++;
               String st = String.format("0x%02X,\t", array[index]);
               result += st;
               if (countBytes > 7) {
                   logger.debug(result);
                   countBytes = 0;
                   result = new String();
                   continue;
               }
           }
           if (!result.isEmpty()) {
               logger.debug(result);
           }

    }

    public static byte setBit(byte byteValue, int i, int j) {
        if (j == 1) {
            return (byte) (byteValue | (1 << i));
        } else {
            return (byte) (byteValue & ~(1 << i));
        }
    }

    public static byte getHighNibble(byte value) {
        return (byte) ((value & 0xF0) >> 4);
    }

    public static byte getLowNibble(byte value) {
        return (byte) (value & 0x0F);
    }

    public static byte[] mergeByteArrays(byte[]... arrays) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            for (byte[] array : arrays) {
                outputStream.write(array);
            }
            byte[] byteArray = outputStream.toByteArray();
            return byteArray;
        } catch (IOException e) {
            logger.error("Exception merging arrays: {}", e);
            return new byte[0];
        }
    }

    public static byte[] intToByteArray(int value) {
        return ByteBuffer.allocate(Integer.SIZE / Byte.SIZE).order(ByteOrder.BIG_ENDIAN).putInt(value).array();
    }

    public static byte[] shortToByteArray(short value) {
        return ByteBuffer.allocate(Short.SIZE / Byte.SIZE).order(ByteOrder.BIG_ENDIAN).putShort(value).array();
    }

}
