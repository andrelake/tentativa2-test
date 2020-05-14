package com.andrelake.gubeetest.api.exceptionhandler;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {

	private Integer status;
	private String tipo;
	private String titulo;
	private String detalhe;
	private List<Campos> campos;
	
	@Getter
	@Builder
	public static class Campos {
		
		private String nome;
		private String mensagemUsuario;
	}
}
