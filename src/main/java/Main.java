import bot.utils.BotUtils;
import dao.AttachmentContant;
import dao.QuestionDatabase;
import dao.TopicDatabase;
import model.*;
import org.checkerframework.checker.units.qual.A;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new bot.Main());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        Scanner scannerStr = new Scanner(System.in);

        while (true){
            System.out.println("1. Add Topic 2.Add question");
            int stepCode = scanner.nextInt();
            switch (stepCode){
                case 1 -> {
                    System.out.println("enter topic name");
                    Topic topic = new Topic();
                    topic.setName(scannerStr.nextLine());
                    System.out.println(new TopicDatabase().addObject(topic));
                }
                case 2 -> {
                    Question question = new Question();
                    Attachment attachment = new Attachment();
                    AttachmentContent attachmentContent = new AttachmentContent();

                    System.out.print("Problem id ni kiriting: ");
                     question.setProblemId( BotUtils.numberScan.nextInt());
                    System.out.println("Savol turini tanlang (1-image/2-text): ");
                    int number = BotUtils.numberScan.nextInt();
                    if(number == 1){
                        question.setType("IMAGE");

                        try {

                            System.out.println("File urlini kiriting:  ");
                            String file = BotUtils.textScan.nextLine();
                            System.out.println("Fileni nomini kiriting: ");
                            attachment.setFileName(BotUtils.textScan.nextLine());
                            FileInputStream fileInputStream = new FileInputStream(file+"/"+attachment.getFileName());
                            byte[] bytes = fileInputStream.readAllBytes();
                            attachmentContent.setContent(bytes);
                            File file1 = new File(file+"/"+attachment.getFileName());
                           attachment.setSize(file1.getFreeSpace());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    }
                    else if(number == 2){
                        question.setType( "TEXT");
                        System.out.println("Savolni matini  kiriting: ");
                       question.setDescription(BotUtils.textScan.nextLine());
                    }
                    else {
                        System.out.println("Xato raqam kiritildi");
                    }
                    System.out.println("Savol orderini kiriting: ");
                   question.setOrder(BotUtils.numberScan.nextInt());
                   System.out.println(new QuestionDatabase().addObjectStange(question,attachmentContent,attachment));
                }
            }
        }
    }
}
