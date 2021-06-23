package com.example.telegrambot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.telegrambot.bot.Robotron;

@Service
public class BotService {
	
	private static final Logger log = LoggerFactory.getLogger(Robotron.class);
	
	@Value("${telegram.bot.name}")
	private  String name;
	
	@Value("${telegram.bot.token}")
	private  String token;
	
//	@PostConstruct
//	public void teste() {
//		ApiContextInitializer.init();
//		var telegramBotsApi = new TelegramBotsApi();
//		try {
//			telegramBotsApi.registerBot(new Bot(name, token));
//		} catch (Exception e) {
//			log.error(e.getMessage());
//		}
//		
//	}

}
