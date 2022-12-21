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

        while (true){
            System.out.println("1. Add Topic 2. ADD problem solved");
            int stepCode = scanner.nextInt();
            switch (stepCode){
                case 1 -> {
                    System.out.println("enter topic name");
                    Topic topic = new Topic();
                    topic.setName(scannerStr.nextLine());
                    System.out.println(new TopicDatabase().addObject(topic));
                }
                case 2 ->{
                    UserProblemStatus problemStatus = new UserProblemStatus();
                    System.out.println("enter user id: ");
                    problemStatus.setUser_id(scanner.nextLong());
                    System.out.println("enter problem id;");
                    problemStatus.setProblem_id(scanner.nextInt());

                    System.out.println(new UserProblemStatusDatabase().addObject(problemStatus));
                }
            }
        }
    }
}
