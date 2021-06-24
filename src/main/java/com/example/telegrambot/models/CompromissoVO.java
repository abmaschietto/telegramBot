package com.example.telegrambot.models;

import java.time.LocalDate;

import org.telegram.telegrambots.meta.api.objects.Update;

import com.example.telegrambot.dto.CompromissoDTO;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class CompromissoVO {
	
	public CompromissoVO(CompromissoDTO dto, Update update) {
		this.nome = dto.getNome();
		this.data = dto.getData();
		this.usuario = update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName();
		this.chatId = update.getMessage().getChatId();
	}
	
	private String nome;
	private LocalDate data;
	private String usuario;
	private Long chatId;

}
