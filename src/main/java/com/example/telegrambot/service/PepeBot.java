package com.example.telegrambot.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.vdurmont.emoji.EmojiParser;

@Service
public class PepeBot {
	
	private Integer messageId = null;
	private static String frogEmoji = EmojiParser.parseToUnicode(":frog:");
	private static String winkEmoji = EmojiParser.parseToUnicode(":wink:");
	static final String PEPE_RESPONSE = "REEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE! " + frogEmoji.repeat(10);
	static final String DEFAULT_RESPONSE = "Não entendi.... você deu o comando certo, seu burro?";
	private static final String START_RESPONSE = "Olá, eu sou o pepe estagiário. Estou aqui para ver porn... ops, quero dizer, te ajudar!"
			+ " Digite /helpepe para ver do que sou capaz! Dica: Não muito!" + winkEmoji;
	static final String COMMANDS = "/helpepe lista todos comandos que pode me dar \n"
								 + "/pepe para uma surpresa pepistica \n"
								 + "/salvarcompromisso salva um compromisso \n"
								 + "/listarcompromissos te mostrará todos seus compromissos";
	private static final String SALVAR_COMPROMISSO = "Qual compromisso deseja salvar e quando? \n"
												+ "Me passe um compromisso e uma data separado por um traço! \n"
												+ "Eu sou burro demais pra fazer de outro jeito! \n"
												+ "Exemplo: Dançar breakdance - 24/07/2021";

	public String simpleResponse(Update update) {
		var response = "";
		if(messageId != null) {
			return followupResponse(update.getMessage().getText(), update.getMessage().getFrom().getFirstName());
		}
		switch (update.getMessage().getText()) {
		case "/pepe":
			response = PEPE_RESPONSE;
			break;
		case "/salvarcompromisso":
			 messageId = update.getMessage().getMessageId();
			response = SALVAR_COMPROMISSO;
			break;
		case "/helpepe":
			response = COMMANDS;
			break;
		case "/start":
			response = START_RESPONSE;
			break;
		default:
			response = DEFAULT_RESPONSE;
			break;
		}
		return response;
	}

	private String followupResponse(String message, String username) {
		String[] splitMessage = message.split("-");
		Matcher m = Pattern.compile("(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d").matcher(splitMessage[1]);
		if(m.find()) {
			messageId = null;
			System.out.println("Salvando no banco...."); //TODO implementar banco de dados
			return "Salvo compromisso: " + splitMessage[0] + "\n"
					+ "No dia: " + m.group() + "\n"
					+ "Para o usuário: " + username;
		}
		return "Data incorreta, sua anta. Você digitou dd/MM/yyy ?";
	}


}