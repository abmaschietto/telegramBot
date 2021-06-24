package com.example.telegrambot.helper;

public enum PepeBotMessagesResponses {
	
	PEPE_RESPONSE  {
	      public String toString() {
	          return  "REEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE! ";
	      }
	  },
	DEFAULT_RESPONSE {
	      public String toString() {
	          return "Não entendi.... você deu o comando certo, seu burro?";
	      }
	  },
	  START_RESPONSE{
	      public String toString() {
	          return "Olá, eu sou o pepe estagiário. Estou aqui para ver porn... ops, quero dizer, te ajudar!"
	      			+ " Digite /helpepe para ver do que sou capaz! Dica: Não muito!";
	      }
	  },
	  COMMANDS{
	      public String toString() {
	          return "/helpepe lista todos comandos que pode me dar \n"
						 + "/pepe para uma surpresa pepistica \n"
						 + "/salvarcompromisso salva um compromisso \n"
						 + "/listarcompromissos te mostrará todos seus compromissos";
	      }
	  },
	  SALVAR_COMPROMISSO_NOME{
	      public String toString() {
	          return "Qual compromisso deseja salvar e quando? \n"
						+ "Me passe o nome do seu compromisso pra eu anotar...";
	      }
	  },
	  SALVAR_COMPROMISSO_DATA{
	      public String toString() {
	          return "Agora me passe uma data, ex:\n"
						+ "30/10/1993";
	      }
	  },
	  DATA_INCORRETA_RESPONSE{
		  public String toString() {
			  return "Data incorreta, sua anta. Você digitou dd/MM/aaaa? Tenta de novo...";
		  }
	  }

}
