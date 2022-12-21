import dao.TopicDatabase;
import dao.UserProblemStatusDatabase;
import model.Topic;
import model.UserProblemStatus;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

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

        UserProblemStatusDatabase problemStatusDatabase = new UserProblemStatusDatabase();
//        System.out.println(problemStatusDatabase.getObjectList().toString());
        for (UserProblemStatus userProblemStatus : problemStatusDatabase.getObjectList()) {
            System.out.println(userProblemStatus);
        }
//        TopicDatabase topicDatabase = new TopicDatabase();
//        for (Topic topic : topicDatabase.getObjectList()) {
//            System.out.println(topic.toString());
//        }
//        while (true){
//            System.out.println("1. Add Topic");
//            int stepCode = scanner.nextInt();
//            switch (stepCode){
//                case 1 -> {
//                    System.out.println("enter topic name");
//                    Topic topic = new Topic();
//                    topic.setName(scannerStr.nextLine());
//                    System.out.println(new TopicDatabase().addObject(topic));
//                }
//            }
//        }
    }
}
