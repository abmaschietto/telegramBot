package com.example.telegrambot.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ActionType;
import org.telegram.telegrambots.meta.api.methods.send.SendChatAction;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.vdurmont.emoji.EmojiParser;

@Component
public class Robotron extends TelegramLongPollingBot {

	private static final Logger log = LoggerFactory.getLogger(Robotron.class);
	private String frogEmoji = EmojiParser.parseToUnicode(":frog:");

	@Value("${telegram.bot.name}")
	private String name;

	@Value("${telegram.bot.token}")
	private String token;

	private String message;

	private String chatId;

	@Override
	public void onUpdateReceived(Update update) {
		message = update.getMessage().getText();
		chatId = update.getMessage().getChatId().toString();
		sendMsg(update);

	}

	@Override
	public String getBotUsername() {
		return this.name;
	}

	@Override
	public String getBotToken() {
		return this.token;
	}

	public synchronized void sendMsg(Update update) {
		var sendMessage = new SendMessage();
		sendMessage.enableMarkdown(true);
		sendMessage.setChatId(this.chatId);
		sendMessage.setText(createResponse(update));
		var sendChatAction = new SendChatAction();
		sendChatAction.setChatId(this.chatId);
		sendChatAction.setAction(ActionType.TYPING);
		try {
			execute(sendChatAction);
			Thread.sleep(1000l);
			execute(sendMessage);
		} catch (TelegramApiException | InterruptedException e) {
			log.error("Exception: ", e.toString());
		}
	}

	private String createResponse(Update update) {
		var response = "";
		switch (this.message) {
		case "/pepe":
			response = "REEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE! " + frogEmoji.repeat(10);
			break;
		case "/salvarcompromisso":
			response = "Qual compromisso deseja salvar e quando? Exemplo: Dançar breakdance no dia 24/07/2021";
			break;
		default:
			response = "Não entendi.... você deu o comando certo, seu burro?";
			break;
		}
		return response;
	}

}
