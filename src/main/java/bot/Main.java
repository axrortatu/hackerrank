package bot;

import bot.utils.BotUtils;
import common.Pair;
import dao.*;
import model.AttachmentContent;
import model.Difficulty;
import model.Problem;
import model.Question;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends TelegramLongPollingBot implements BotConstants {

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
                        BotUtils.buildReplyMarkup(List.of(TOPIC, PREPARATION), 2)
                );
                botExecute(MessageType.SEND_MESSAGE, sendMessage);
            } else if (TEXT.equals(TOPIC)) {

                InlineKeyboardMarkup inlineKeyboardMarkup = BotUtils.buildInlineMarkup(
                        new ArrayList<>(new TopicDatabase().getObjectList()), 2);
                SendMessage sendMessage = BotUtils.buildSendMessage(
                        CHAT_ID,
                        "select topics",
                        inlineKeyboardMarkup,
                        null
                );
                botExecute(MessageType.SEND_MESSAGE, sendMessage);
            }
        } else if (update.hasCallbackQuery()) {
            String callBackData = update.getCallbackQuery().getData();
            Message callBackMessage = update.getCallbackQuery().getMessage();
            Long chatId = callBackMessage.getChatId();
            Integer messageId = callBackMessage.getMessageId();

            if (isTopic(callBackData)) {
                String topicId = callBackData.substring(TOPIC.length());
                InlineKeyboardMarkup inlineKeyboardMarkup = BotUtils.buildInlineMarkup(List.of(
                        new Pair<>(Difficulty.EASY, topicId),
                        new Pair<>(Difficulty.MEDIUM, topicId),
                        new Pair<>(Difficulty.HARD, topicId),
                        new Pair<>(Difficulty.ALL, topicId)
                ), 2);
                EditMessageText editMessageText = BotUtils.buildEditMessage(
                        messageId,
                        chatId,
                        "select one type of problem",
                        inlineKeyboardMarkup
                );
                botExecute(MessageType.EDIT_MESSAGE, editMessageText);
            } else if (isProblem(callBackData)) {
                pageNumberList.put(callBackMessage.getChatId(), callBackData.replace(PROBLEM, PREV));
                test(chatId, messageId,true);
                BotConstants.ADMIN_SEND_QUESTION_CONTENT.put(chatId, BotConstants.ADMIN_SEND_QUESTION);
            } else if (isPrevOrNext(callBackData)) {
                if (callBackData.startsWith(PREV)) {
                    test(chatId, messageId, true);
                }else{
                    test(chatId, messageId, false);
                }
            } else if (BotConstants.ADMIN_SEND_QUESTION_CONTENT.get(chatId)!=null&&BotConstants.ADMIN_SEND_QUESTION_CONTENT.get(chatId).equals(BotConstants.ADMIN_SEND_QUESTION)) {
                sendQuestionContent(chatId, callBackData);
            }


        }
    }

    private void sendQuestionContent(Long chatId, String data) {
        List<Question> questions = new QuestionDatabase().getObjectList(Integer.valueOf(data));

        for (Question question : questions) {
            List<model.Attachment> attachments = new Attachment().getObjectList(question.getAttachmentId());
            model.Attachment attachment = attachments.get(0);
            List<AttachmentContent> attachmentContents = new AttachmentContant().getObjectList((int) attachment.getAttachmentId());
            AttachmentContent attachmentContent = attachmentContents.get(0);

            String description = question.getDescription();
            byte[] contentByte = attachmentContent.getContent();

            if (contentByte!=null){
                try (FileOutputStream outputStream = new FileOutputStream("src/image.jpg")) {

                    outputStream.write(contentByte);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                ByteArrayInputStream inputStream=new ByteArrayInputStream(contentByte);
                InputFile inputFile = new InputFile(inputStream, "src/image.jpg");
                SendPhoto sendPhoto = new SendPhoto(String.valueOf(chatId), inputFile);
                botExecute(MessageType.SEND_PHOTO, sendPhoto);
            }



            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText(description);
            botExecute(MessageType.SEND_MESSAGE, sendMessage);

        }



    }

    private void botExecute(MessageType messageType, Object object) {
        try {
            switch (messageType) {
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

        if (isPrev && page > 1){
            page --;
        }
        if (!isPrev){
            page ++;
        }

        ProblemDatabase problemDatabase = new ProblemDatabase();
        String problemListInfo = problemDatabase.getProblemInfo(topicId, Difficulty.valueOf(difficulty), page);
        List<Problem> problemList = problemDatabase.getProblemByTopicId(
                Integer.parseInt(topicId),
                Difficulty.valueOf(difficulty),
                page);


        InlineKeyboardMarkup inlineKeyboardMarkup = BotUtils.buildInlineMarkup(new ArrayList<>(problemList), 3);
        EditMessageText editMessageText = BotUtils.buildEditMessage(
                messageId,
                chatId,
                problemListInfo,
                inlineKeyboardMarkup
        );



        pageNumberList.put(chatId, (PREV + SEPARATOR + topicId + SEPARATOR + difficulty + SEPARATOR + page));
        botExecute(MessageType.EDIT_MESSAGE, editMessageText);
    }


}
