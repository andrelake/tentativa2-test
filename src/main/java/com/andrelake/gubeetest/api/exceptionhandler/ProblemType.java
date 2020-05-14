package com.andrelake.gubeetest.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	RECURSO_NAO_ENCONTRADO("Recurso não encontrado"),
	RECURSO_EM_USO("Recurso em uso"),
	MENSAGEM_INCOMPREENSIVEL("Mensagem incompreensível"),
	URL_INVALIDA("URL inválida"),
	DADOS_INVALIDOS("Dados inválidos"),
	ERRO_DE_SISTEMA("Erro de sistema");
	
	private String titulo;
	
	ProblemType(String titulo) {
		this.titulo = titulo;
	}
}
