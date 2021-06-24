package com.example.telegrambot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import com.example.telegrambot.dto.CompromissoDTO;
import com.example.telegrambot.models.CompromissoVO;

@Service
public class CompromissoService {
	
	//retorna nulo para zerar o compromissoDTO do bot e permitir novas respostas
	public CompromissoDTO convertDtoToVO(CompromissoDTO dto, Update update) {
		CompromissoVO compromissoVO = new CompromissoVO(dto, update);
		System.out.println(compromissoVO.toString());
		return null;
	}

}
