package practice2.task3;

import java.nio.ByteBuffer;

public class Counter {
    public int cCRC16(ByteBuffer buf) {
        int i;
        int crc_value = 0;
        for (int len = 0; len < buf.position(); len++) {
            for (i = 0x80; i != 0; i >>= 1) {
                if ((crc_value & 0x8000) != 0) {
                    crc_value = (crc_value << 1) ^ 0x8005;
                } else {
                    crc_value = crc_value << 1;
                }
                if ((buf.get(len) & i) != 0) {
                    crc_value ^= 0x8005;
                }
            }
        }
        return crc_value;
    }
}
