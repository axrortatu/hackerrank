package bot;

import java.util.HashMap;
import java.util.Map;

public class BotLanguange {

    public static Map<Long, String> USER_LANGUANGE_NAME = new HashMap<>();



    public void setLanguange(Long chatId, Boolean isFalseOrTrue, String languangeName){
        if (USER_LANGUANGE_NAME.containsKey(chatId)){
                USER_LANGUANGE_NAME.put(chatId, languangeName);
        }else{
                USER_LANGUANGE_NAME.put(chatId, languangeName);
        }
    }


    public String getLanguangeName(Long chatId){
            String str =  USER_LANGUANGE_NAME.get(chatId);
            return str;
    }

}
