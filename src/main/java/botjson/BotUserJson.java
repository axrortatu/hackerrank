package botjson;

import bot.BotLanguange;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.Map;

public class BotUserJson {


    BotLanguange botLang = new BotLanguange();


    public void userJsonRead(){
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get("/src/main/resources/BotJson.json"));
            botLang.USER_LANGUANGE_NAME = gson.fromJson(reader,  new TypeToken<Map<Long, String>>() {}.getType());
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void userJsonWriter(){
        try (FileWriter file = new FileWriter("/src/main/resources/BotJson.json")) {
            Gson gsonObj = new Gson();
            String jsonStr = gsonObj.toJson(botLang.USER_LANGUANGE_NAME);
            file.write(jsonStr);
            file.flush();
            System.out.println("Map data successfully written to the Sample.json file.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
