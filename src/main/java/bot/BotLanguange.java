package bot;

import java.util.HashMap;
import java.util.Map;

public class BotLanguange {

    public static Map<Long, String> USER_LANGUANGE_NAME = new HashMap<>();



    public void setLanguage(Long chatId, Boolean isFalseOrTrue, String languangeName){
                USER_LANGUANGE_NAME.put(chatId, languangeName);
    }


    public String getLanguageName(Long chatId){
        return USER_LANGUANGE_NAME.get(chatId);
    }

}
