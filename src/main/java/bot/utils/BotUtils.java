package bot.utils;

import bot.BotConstants;
import common.Pair;
import model.Problem;
import model.Topic;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public abstract class BotUtils implements BotConstants {

    public static ReplyKeyboardMarkup buildReplyMarkup(final List<String> menuList, final int column) {
        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        final List<KeyboardRow> keyboardRowList = new ArrayList<>();
        replyKeyboardMarkup.setKeyboard(keyboardRowList);

        KeyboardRow keyboardRow = new KeyboardRow();
        for (int i = 0; i < menuList.size(); i++) {
            keyboardRow.add(new KeyboardButton(menuList.get(i)));

            if (i % column == 0) {
                keyboardRowList.add(keyboardRow);
                keyboardRow = new KeyboardRow();
            }
        }
        if (!keyboardRow.isEmpty()) {
            keyboardRowList.add(keyboardRow);
        }
        return replyKeyboardMarkup;
    }

    public static InlineKeyboardMarkup buildInlineMarkup(final List<Object> objectList, final int column) {
        return new InlineKeyboardMarkup(
                BotUtils.getInlineKeyboardRowList(objectList, column)
        );
    }

    public static SendMessage buildSendMessage(Long chatId, String text, InlineKeyboardMarkup i, ReplyKeyboardMarkup r) {
        SendMessage sendMessage = new SendMessage(chatId.toString(), text);
        if (i != null) {
            sendMessage.setReplyMarkup(i);
        }
        if (r != null) {
            sendMessage.setReplyMarkup(r);
        }
        return sendMessage;
    }

    public static EditMessageText buildEditMessage(Integer messageId, Long chatId, String text, InlineKeyboardMarkup i) {
        EditMessageText editMessage = new EditMessageText(text);
        editMessage.setMessageId(messageId);
        editMessage.setChatId(chatId);
        if (i != null) {
            editMessage.setReplyMarkup(i);
        }
        return editMessage;
    }

    private static List<List<InlineKeyboardButton>> getInlineKeyboardRowList(List<Object> objectList, int column) {
        List<List<InlineKeyboardButton>> list = new ArrayList<>();
        List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
        int index = 0;
        for (Object object : objectList) {
            if (object instanceof Topic topic) {
                InlineKeyboardButton button = new InlineKeyboardButton();
                button.setText(topic.getName());
                button.setCallbackData(TOPIC + topic.getId());
                inlineKeyboardButtons.add(button);
            } else if (object instanceof Pair pair) {
                InlineKeyboardButton button = new InlineKeyboardButton();
                button.setText(pair.getKey().toString());
                button.setCallbackData(PROBLEM + SEPARATOR + pair.getValue() + SEPARATOR + pair.getKey() + SEPARATOR + "1");
                inlineKeyboardButtons.add(button);
            } else if (object instanceof Problem problem) {
                InlineKeyboardButton button = new InlineKeyboardButton();
                button.setText(String.valueOf(index + 1));
                button.setCallbackData(String.valueOf(problem.getId()));
                inlineKeyboardButtons.add(button);
                if (index + 1 == objectList.size()) {
                    list.add(inlineKeyboardButtons);
                    inlineKeyboardButtons = new ArrayList<>();

                    InlineKeyboardButton prev = new InlineKeyboardButton();
                    prev.setText(PREV);
                    prev.setCallbackData(PREV);
                    inlineKeyboardButtons.add(prev);

                    InlineKeyboardButton next = new InlineKeyboardButton();
                    next.setText(NEXT);
                    next.setCallbackData(NEXT);
                    inlineKeyboardButtons.add(next);
                }
            } else if (object instanceof String str) {
                InlineKeyboardButton button = new InlineKeyboardButton();
                button.setText(str);
                button.setCallbackData(str.toUpperCase());
                inlineKeyboardButtons.add(button);
            }
            if ((index + 1) % column == 0) {
                list.add(inlineKeyboardButtons);
                inlineKeyboardButtons = new ArrayList<>();
            }
            index++;
        }
        if (!inlineKeyboardButtons.isEmpty()) {
            list.add(inlineKeyboardButtons);
        }
        return list;
    }
    public static List<List<InlineKeyboardButton>> getInlineKeyboardRowListOfTopic(List<Object> objectList, int column) {
        List<List<InlineKeyboardButton>> list = new ArrayList<>();
        List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
        int index = 0;
        for (Object o : objectList) {
            if(o instanceof Topic topic){
                InlineKeyboardButton button = new InlineKeyboardButton();
                button.setText(String.valueOf(index + 1));
                button.setCallbackData(TOPIC_ID + SEPARATOR + topic.getId());
                inlineKeyboardButtons.add(button);
            }
            if ((index + 1) % column == 0) {
                list.add(inlineKeyboardButtons);
                inlineKeyboardButtons = new ArrayList<>();
            }
            index++;
        }
        if (!inlineKeyboardButtons.isEmpty()) {
            list.add(inlineKeyboardButtons);
        }
        return list;
    }
    public static InlineKeyboardMarkup getInlineKeyboardMarkup(String topicId) {
        List<List<InlineKeyboardButton>> list = new ArrayList<>();
        List<InlineKeyboardButton> inlineKeyboardButtons = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(BotConstants.CONTINUE);
        button.setCallbackData(TOPIC + topicId);
        inlineKeyboardButtons.add(button);
        list.add(inlineKeyboardButtons);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(list);
        return inlineKeyboardMarkup;
    }
}
