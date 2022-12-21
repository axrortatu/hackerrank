package bot;

import java.util.HashMap;

public interface BotConstants {
    String USERNAME = "t.me/mypdpbot";
    String BOT_TOKEN = "5521074437:AAGUFYPjqzZ54I2ivcSE_qm2LLDnYo1vLoo";

    String EASY = "easy";
    String MEDIUM = "medium";
    String HARD = "hard";
    String ALL = "all";


    String START = "/start";
    String TOPIC = "topic";
    String TOPIC_ID = "TopicId";
    String PROBLEM = "problem";
    String PREPARATION = "preparation";
    String SELECT_TOPIC = "SELECT_TOPIC";

    String CONTINUE = "Continue preparation";

    int OBJECTID = 1;
    int DIFFICULTY = 2;
    String SEPARATOR = "/";
    int PAGE_NUMBER = 3;

    String NEXT = "⏭";
    String PREV = "⏮";

    HashMap<Long, String> USER_STATUS = new HashMap<>();


    default boolean isTopic(final String callBackData) {
        return callBackData.startsWith(TOPIC);
    }

    default boolean isProblem(final String callBackData) {
        return callBackData.startsWith(PROBLEM);
    }
    default boolean isPrevOrNext(final String callBackData) {
        return callBackData.startsWith(PREV) || callBackData.startsWith(NEXT);
    }

    default boolean isTopicId(String callBackData) {
        return callBackData.startsWith(TOPIC_ID);
    }
}
