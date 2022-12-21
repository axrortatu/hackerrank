package bot;

public class BotDifficultyLanguange {

    String TOPIC = "topic";
    String PROBLEM = "problem";
    String BACK = " back ";

    static BotDifficultyLanguange bDL = new BotDifficultyLanguange();

    String PREPARATION = "preparation";

    BotLanguange botLang = new BotLanguange();

    String[] difficultyLang = new String[4];
    String[] themeType = new String[3];



    public String[] getThemeType(Long CHAT_ID){
        if (botLang.getLanguangeName(CHAT_ID) == "ENG"){
            themeType = new String[]{"topic", "preparation", "back"};
        }
        else if (botLang.getLanguangeName(CHAT_ID) == "UZB") {
            themeType = new String[]{"mavzu", "mavzu malumotlari", "orqaga"};
        }
        else {
            themeType = new String[]{"тема", "информация о теме", "назад"};
        }

        return themeType;
    }

    public String[] getDifficultyType(Long CHAT_ID){

        if (botLang.getLanguangeName(CHAT_ID) == "ENG"){
            difficultyLang = new String[]{"EASY", "MEDIUM", "HARD", "ALL"};
        }
        else if (botLang.getLanguangeName(CHAT_ID) == "UZB") {
            difficultyLang = new String[]{"OSON", "O'RTA", "QIYIN", "HAMMASI"};
        }
        else {
            difficultyLang = new String[]{"ЛЕГКИЙ", "СРЕДНИЙ", "СЛОЖНЫЙ", "ВСЕ"};
        }

        return difficultyLang;
    }

}
