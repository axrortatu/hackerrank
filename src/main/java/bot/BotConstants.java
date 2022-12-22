package bot;

import common.Pair;
import model.Difficulty;

import java.util.HashMap;

public interface BotConstants {
    String USERNAME = "test1_pdp_lesson10_bot";
    String BOT_TOKEN = "5703788864:AAG1SibrUYxlB3C6ZrLmNn9PM8bEx1WE4so";

    String EASY = "easy";
    String MEDIUM = "medium";
    String HARD = "hard";
    String ALL = "all";


    String START = "/start";
    String TOPIC = "topic";
    String PROBLEM = "problem";
    String BACK = " back ";

    String PREPARATION = "preparation";
    String SELECT_TOPIC = "SELECT_TOPIC";

    int OBJECTID = 1;
    int DIFFICULTY = 2;
    String SEPARATOR = "/";
    int PAGE_NUMBER = 3;

    String NEXT = "⏭";
    String PREV = "⏮";
    String SOLVED = "SOLVED";
    String UNSOLVED = "UNSOLVED";

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
    default boolean isProblemSolvedOrUnsolved(final String callBackData){
        return callBackData.contains(UNSOLVED) || callBackData.contains(SOLVED);
    }
}
