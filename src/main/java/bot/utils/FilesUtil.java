package bot.utils;

import java.io.FileInputStream;
import java.io.IOException;

public class FilesUtil {
    public static byte[] sendBytes(String fileName, String fileUrl){

        try {
            FileInputStream fileInputStream = new FileInputStream(fileUrl+"/"+fileName);
            return fileInputStream.readAllBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
