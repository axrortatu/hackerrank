package bot;

import common.Pair;
import model.Difficulty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public interface BotConstants {
    String USERNAME = "http://t.me/kjabsdfblasdfbbot";
    String BOT_TOKEN = "5806614401:AAGylqQwXXYsPcOgCuopA32e6d7Cw7f5RNE";

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


    String PROBLEM = "problem";
    String BACK = " back ";


    String SELECT_TOPIC = "SELECT_TOPIC";

    int OBJECTID = 1;
    int DIFFICULTY = 2;
    String SEPARATOR = "/";
    int PAGE_NUMBER = 3;

    String NEXT = "⏭";
    String PREV = "⏮";

    String TEXT = "TEXT";
    String IMAGE = "IMAGE";

    HashMap<Long, String> USER_STATUS = new HashMap<>();
    HashMap<Long, String> ADMIN_SEND_QUESTION_CONTENT = new HashMap<>();
    String ADMIN_SEND_QUESTION = "ADMIN_SEND_QUESTION";

    default boolean isTopic(final String callBackData ) {
        return callBackData.startsWith(TOPIC);
    }


    default boolean isProblem(final String callBackData) {
        return callBackData.startsWith(PROBLEM);
    }

    default boolean isPrevOrNext(final String callBackData) {
        return callBackData.startsWith(PREV) || callBackData.startsWith(NEXT);
    }
}
