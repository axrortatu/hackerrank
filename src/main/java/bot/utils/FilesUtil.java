package bot.utils;

import bot.BotConstants;
import common.Pair;
import dao.AttachmentContantDatabase;
import dao.AttachmentDatabase;
import model.Attachment;
import model.AttachmentContent;
import model.Question;
import org.telegram.telegrambots.meta.api.objects.InputFile;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class FilesUtil {
    public static Pair<String, InputFile> getPair(List<Question> questions) {

        InputFile inputFile = null;
        String decription = null;

        for (Question question : questions) {
            if (question.getType().equals(BotConstants.IMAGE)) {
                Attachment attachment = new AttachmentDatabase().getObjectById(question.getAttachmentId());
                AttachmentContent attachmentContent = new AttachmentContantDatabase().getObjectById((int) attachment.getAttachmentId());
                byte[] contentByte = attachmentContent.getContent();

                if (contentByte != null) {
                    try (FileOutputStream outputStream = new FileOutputStream("src/image.jpg")) {
                        outputStream.write(contentByte);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ByteArrayInputStream inputStream = new ByteArrayInputStream(contentByte);
                    inputFile = new InputFile(inputStream, "src/image.jpg");
                }
            } else if (question.getType().equals(BotConstants.TEXT)) {
                decription = question.getDescription();
            }
        }
        return new Pair<>(decription, inputFile);
    }

    public static byte[] getBytes(String fileName, String fileUrl) {

        try {
            FileInputStream fileInputStream = new FileInputStream(fileUrl + "/" + fileName);
            return fileInputStream.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

