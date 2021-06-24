package com.example.telegrambot.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class CompromissoDTO {
	
	private String nome;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private LocalDate data;
	
	public void setData(String data) {
		var formato = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
		this.data = LocalDate.parse(data, formato); 
	}

}
