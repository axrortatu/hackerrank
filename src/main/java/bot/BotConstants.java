package bot;

import common.Pair;
import model.Difficulty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public interface BotConstants {

    String USERNAME = "hackerrank_project_bot";
    String BOT_TOKEN = "5961284561:AAHYEz4bnzTV5K3gcWhcGYd6EVziwNZIFk4";

    String EASY_ENG = "EASY";
    String MEDIUM_ENG = "MEDIUM";
    String HARD_ENG = "HARD";
    String ALL_ENG = "ALL";

    String EASY_UZB = "OSON";
    String MEDIUM_UZB = "O'RTA";
    String HARD_UZB = "QIYIN";
    String ALL_UZB = "HAMMASI";

    String EASY_RUS = "ЛЕГКИЙ";
    String MEDIUM_RUS = "СРЕДНИЙ";
    String HARD_RUS = "СЛОЖНЫЙ";
    String ALL_RUS = "ВСЕ";


    String TOPIC = "topic";
    String PREPARATION = "preparation";
    String TOPIC_UZB = "mavzu";
    String PREPARATION_UZB = "mavzu malumotlari";
    String BACK_UZB = "orqaga";
    String TOPIC_RUS = "тема";
    String PREPARATION_RUS = "информация о теме";
    String BACK_RUS = "назад";



    String UZB = "UZB";
    String RUS = "RUS";
    String ENG = "ENG";




    String START = "/start";

    String TOPIC_ID = "TopicId";

    String PROBLEM = "problem";
    String BACK = " back ";


    String SELECT_TOPIC = "SELECT_TOPIC";

    String CONTINUE = "Continue preparation";

    int OBJECTID = 1;
    int DIFFICULTY = 2;
    String SEPARATOR = "/";
    int PAGE_NUMBER = 3;

    String SOLVED = " ✅SOLVED";
    String NOT_RESOLVED = " ❌NOT RESOLVED";

    String NEXT = "⏭";
    String PREV = "⏮";

    String TEXT = "TEXT";
    String IMAGE = "IMAGE";

    HashMap<Long, String> USER_STATUS = new HashMap<>();
    HashMap<Long, String> ADMIN_SEND_QUESTION_CONTENT = new HashMap<>();
    String ADMIN_SEND_QUESTION = "ADMIN_SEND_QUESTION";
    String SEND_CONTACT = "share_contact";
    String SHARE_CONTACT = "SHARE CONTACT";

    default boolean isTopic(final String callBackData ) {
        return callBackData.startsWith(TOPIC);
    }


    default boolean isProblem(final String callBackData) {
        return callBackData.startsWith(PROBLEM);
    }

    default boolean isPagination(final String callBackData) {
        return callBackData.startsWith(PREV) || callBackData.startsWith(NEXT);
    }
    default boolean isProblemSolvedOrUnsolved(final String callBackData){
        return callBackData.contains(UNSOLVED) || callBackData.contains(SOLVE);
    }

    default boolean isPreparation(String callBackData) {
        return callBackData.startsWith(TOPIC_ID);
    }
}
