package com.example.telegrambot.service;

import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.example.telegrambot.dto.CompromissoDTO;
import com.example.telegrambot.helper.PepeBotMessagesResponses;
import com.vdurmont.emoji.EmojiParser;

@Service
public class PepeBotTasks {
	
	@Autowired
	private CompromissoService compromissoService;
	private static final Logger log = LoggerFactory.getLogger(PepeBotTasks.class);
	private CompromissoDTO currentCompromisso = null;
	private final String frogEmoji = EmojiParser.parseToUnicode(":frog:");
	private static String winkEmoji = EmojiParser.parseToUnicode(":wink:");

	public String simpleResponse(Update update) {
		var response = "";
		if(currentCompromisso != null) {
			return followupResponse(update);
		}
		switch (update.getMessage().getText()) {
		case "/pepe":
			response =  PepeBotMessagesResponses.PEPE_RESPONSE.toString() + frogEmoji.repeat(10);
			break;
		case "/salvarcompromisso":
			currentCompromisso = new CompromissoDTO();
			response = PepeBotMessagesResponses.SALVAR_COMPROMISSO_NOME.toString();
			break;
		case "/helpepe":
			response = PepeBotMessagesResponses.COMMANDS.toString();
			break;
		case "/start":
			response = PepeBotMessagesResponses.START_RESPONSE.toString() + winkEmoji;
			break;
		default:
			response = PepeBotMessagesResponses.DEFAULT_RESPONSE.toString();
			break;
		}
		return response;
	}

	private String followupResponse(Update update) {
		var message = update.getMessage().getText();
		if(currentCompromisso.getNome() == null  && currentCompromisso.getData() == null) {
			currentCompromisso.setNome(message);
			System.out.println(currentCompromisso.toString());
			return PepeBotMessagesResponses.SALVAR_COMPROMISSO_DATA.toString();
		}
		else if(!currentCompromisso.getNome().isBlank() && currentCompromisso.getData() == null) {
			currentCompromisso.setData(message);
			var response = endDialogueResponse(currentCompromisso);
			this.currentCompromisso = compromissoService.convertDtoToVO(currentCompromisso, update); 
			return "Tudo certo! \n" + response;
			}
		return PepeBotMessagesResponses.DATA_INCORRETA_RESPONSE.toString();
	}
	
	private String endDialogueResponse(CompromissoDTO currentCompromisso) {
		var formato = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
		var response = "Compromisso salvo! \n"
					+  "Compromisso: " + currentCompromisso.getNome() + "\n"
					+  "No dia: " + formato.format(currentCompromisso.getData());
		return response;
	}


}