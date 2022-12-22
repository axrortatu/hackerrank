package bot;

import common.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static bot.BotConstants.*;

public class BotDifficultyLanguange {
    BotLanguange botLang = new BotLanguange();


    public List<String> getThemeType(Long CHAT_ID){
        List<String> themeType = new ArrayList<>();
        if (botLang.getLanguangeName(CHAT_ID).equals(ENG)){
            themeType.addAll(List.of(TOPIC, PREPARATION, BACK));
        }
        else if (botLang.getLanguangeName(CHAT_ID).equals(UZB)) {
            themeType.addAll(List.of(TOPIC_UZB, PREPARATION_UZB, BACK_UZB));
        }
        else {
            themeType.addAll(List.of(TOPIC_RUS, PREPARATION_RUS, BACK_RUS));
        }
        List<String> getThemeButton = new ArrayList<>();
        for (int i = 0; i < themeType.size(); i++) {
            getThemeButton.addAll(Collections.singleton(themeType.get(i)));
        }
        return getThemeButton;
    }

    public List<Object> getDifficultyType(Long CHAT_ID, int topicId){
        List<String> difficultyLang = new ArrayList<>();
        if (botLang.getLanguangeName(CHAT_ID).equals(ENG)){
            difficultyLang.addAll(List.of(EASY_ENG,MEDIUM_ENG,HARD_ENG,ALL_ENG));
        }
        else if (botLang.getLanguangeName(CHAT_ID).equals(UZB)) {
            difficultyLang.addAll(List.of(EASY_UZB,MEDIUM_UZB,HARD_UZB,ALL_UZB));

        }
        else {
            difficultyLang.addAll(List.of(EASY_RUS,MEDIUM_RUS,HARD_RUS,ALL_RUS));
        }
        List<Object> pairList = new ArrayList<>();
        for (int i = 0; i < difficultyLang.size(); i++) {
            pairList.add(new Pair<>(difficultyLang.get(i),topicId));
        }
        return pairList;
    }
}
