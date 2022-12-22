package bot;

import bot.utils.BotUtils;
import bot.utils.FilesUtil;
import common.Pair;
import dao.ProblemDatabase;
import dao.TopicDatabase;
import dao.UserPreparationDataBase;
import model.Difficulty;
import model.Problem;
import dao.*;
import model.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.*;


public class Main extends TelegramLongPollingBot implements BotConstants {

    BotLanguange languange = new BotLanguange();

    BotDifficultyLanguange botDifLang = new BotDifficultyLanguange();

    Map<Long, String> pageNumberList = new HashMap<>();

    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {

            Message message = update.getMessage();
            String TEXT = message.getText();
            Long CHAT_ID = message.getChatId();

            if (TEXT.equals(START)) {
                SendMessage sendMessage = BotUtils.buildSendMessage(
                        CHAT_ID,
                        "welcome our bot !!!",
                        null,
                        BotUtils.buildReplyMarkup(List.of(UZB, RUS, ENG), 2)
                );
                botExecute(MessageType.SEND_MESSAGE, sendMessage);
            } else if (TEXT.equals(UZB)) {
                languange.setLanguange(CHAT_ID, true, UZB);
                SendMessage sendMessage = BotUtils.buildSendMessage(
                        CHAT_ID,
                        "O'zbek tiliga almashdi !!!",
                        null,
                        BotUtils.buildReplyMarkup(botDifLang.getThemeType(CHAT_ID), 2)
                );
                botExecute(MessageType.SEND_MESSAGE, sendMessage);
            } else if (TEXT.equals(ENG)) {
                languange.setLanguange(CHAT_ID, true, ENG);
                SendMessage sendMessage = BotUtils.buildSendMessage(
                        CHAT_ID,
                        "Changed to English !!!",
                        null,
                        BotUtils.buildReplyMarkup(botDifLang.getThemeType(CHAT_ID), 2)
                );
                botExecute(MessageType.SEND_MESSAGE, sendMessage);
            } else if (TEXT.equals(RUS)) {
                languange.setLanguange(CHAT_ID, true, RUS);
                SendMessage sendMessage = BotUtils.buildSendMessage(
                        CHAT_ID,
                        "Перешел на русский !!!",
                        null,
                        BotUtils.buildReplyMarkup(botDifLang.getThemeType(CHAT_ID), 2)
                );
                botExecute(MessageType.SEND_MESSAGE, sendMessage);
            } else if (TEXT.equals(botDifLang.getThemeType(CHAT_ID).get(0))) {
                String str = "";
                if (languange.getLanguangeName(CHAT_ID).equals(ENG)) str = "Select topics !";
                else if (languange.getLanguangeName(CHAT_ID).equals(UZB)) str = "Mavzularni tanlang !";

                else str = "Выберите темы !";

                InlineKeyboardMarkup inlineKeyboardMarkup = BotUtils.buildInlineMarkup(
                        new ArrayList<>(new TopicDatabase().getObjectList()), 2);
                SendMessage sendMessage = BotUtils.buildSendMessage(
                        CHAT_ID,
                        str,
                        inlineKeyboardMarkup,
                        null
                );
                botExecute(MessageType.SEND_MESSAGE, sendMessage);
            } else if (TEXT.equals(PREPARATION)) {
                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(
                        BotUtils.getInlineKeyboardRowListOfTopic(new ArrayList<>(new TopicDatabase().getObjectList()), 3));
                SendMessage sendMessage = BotUtils.buildSendMessage(CHAT_ID, new TopicDatabase().getTopics(), inlineKeyboardMarkup, null);
                botExecute(MessageType.SEND_MESSAGE, sendMessage);
            }
        } else if (update.hasCallbackQuery()) {
            String callBackData = update.getCallbackQuery().getData();
            Message callBackMessage = update.getCallbackQuery().getMessage();
            Long chatId = callBackMessage.getChatId();
            Integer messageId = callBackMessage.getMessageId();

            if (isTopic(callBackData)) {
                String topicId = callBackData.substring(TOPIC.length());
                InlineKeyboardMarkup inlineKeyboardMarkup = BotUtils.buildInlineMarkup(
                        botDifLang.getDifficultyType(chatId, Integer.parseInt(topicId)), 2);
                EditMessageText editMessageText = BotUtils.buildEditMessage(
                        chatId,
                        languange.getLanguangeName(chatId).equals(ENG) ? "Select one type of problem" :
                                languange.getLanguangeName(chatId).equals(UZB) ? "Muammoning bir turini tanlang" :

                                        "Bыберите один тип проблемы ",
                        messageId,
                        inlineKeyboardMarkup
                );
                botExecute(MessageType.EDIT_MESSAGE, editMessageText);
            } else if (isProblem(callBackData)) {
                pageNumberList.put(callBackMessage.getChatId(), callBackData.replace(PROBLEM, PREV));
                test(chatId, messageId, true);
                BotConstants.ADMIN_SEND_QUESTION_CONTENT.put(chatId, BotConstants.ADMIN_SEND_QUESTION);
            } else if (isPrevOrNext(callBackData)) {
                if (callBackData.startsWith(PREV)) {
                    test(chatId, messageId, true);
                } else {
                    test(chatId, messageId, false);
                }
            } else if (isTopicId(callBackData)) {
                String[] split = callBackData.split(SEPARATOR);
                String topicId = split[OBJECTID];
                String userPreparation = new UserPreparationDataBase().getUserPreparation(chatId, Integer.parseInt(topicId));
                InlineKeyboardMarkup inlineKeyboardMarkup = BotUtils.getInlineKeyboardMarkup(topicId);
                EditMessageText editMessageText =
                        BotUtils.buildEditMessage(
                                chatId,
                                userPreparation,
                                messageId,
                                inlineKeyboardMarkup
                        );
                botExecute(MessageType.EDIT_MESSAGE, editMessageText);
            } else if (BotConstants.ADMIN_SEND_QUESTION_CONTENT.get(chatId) != null && BotConstants.ADMIN_SEND_QUESTION_CONTENT.get(chatId).equals(BotConstants.ADMIN_SEND_QUESTION)) {
                Pair<String, InputFile> pair = FilesUtil.getPair(new QuestionDatabase().getObjectList(Integer.parseInt(callBackData)));
                SendPhoto sendPhoto = BotUtils.buildSendPhoto(
                        chatId,
                        pair.getKey(),
                        pair.getValue()
                );
                botExecute(MessageType.SEND_PHOTO, sendPhoto);
            }
        }
    }


    private void botExecute(MessageType messageType, Object object) {
        try {
            switch (messageType) {
                case SEND_PHOTO -> execute((SendPhoto) object);
                case SEND_MESSAGE -> execute((SendMessage) object);
                case EDIT_MESSAGE -> execute((EditMessageText) object);
                case DELETE_MESSAGE -> execute((DeleteMessage) object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void test(Long chatId, int messageId, boolean isPrev) {

        String[] split = pageNumberList.get(chatId).split(SEPARATOR);
        String topicId = split[OBJECTID];
        String difficulty = split[DIFFICULTY];
        String pageNumber = split[PAGE_NUMBER];

        int page = Integer.parseInt(pageNumber);

        if (isPrev && page > 1) {
            page--;
        }
        if (!isPrev) {
            page++;
        }

        ProblemDatabase problemDatabase = new ProblemDatabase();
        String problemListInfo = problemDatabase.getProblemInfo(topicId, Difficulty.valueOf(difficulty), page);
        List<Problem> problemList = problemDatabase.getProblemByTopicId(
                Integer.parseInt(topicId),
                Difficulty.valueOf(difficulty),
                page);

        InlineKeyboardMarkup inlineKeyboardMarkup = BotUtils.buildInlineMarkup(new ArrayList<>(problemList), 3);
        EditMessageText editMessageText = BotUtils.buildEditMessage(
                chatId,
                problemListInfo,
                messageId,
                inlineKeyboardMarkup
        );

        pageNumberList.put(chatId, (PREV + SEPARATOR + topicId + SEPARATOR + difficulty + SEPARATOR + page));
        botExecute(MessageType.EDIT_MESSAGE, editMessageText);
    }


}
