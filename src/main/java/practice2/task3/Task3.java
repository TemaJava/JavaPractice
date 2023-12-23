package practice2.task3;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class Task3 {

    public static void main(String[] args) throws IOException {
        byte[] byteStr = new byte[4];
        File file = new File("C:/Users/User/dev/practiceJava/src/main/java/practice2/task3/test.txt");
        FileInputStream fis = new FileInputStream(file);

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(fis.readAllBytes());
        int crcRes = new Counter().cCRC16(byteBuffer);
        System.out.println(Integer.toHexString(crcRes));
        byteStr[0] = (byte) (crcRes & 0x000000ff);
        byteStr[1] = (byte) ((crcRes & 0x0000ff00) >>> 8);
        System.out.printf("%02X\n%02X", byteStr[0], byteStr[1]);}
}


