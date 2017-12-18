package com.tecnolpet.ot.dto;

import java.io.Serializable;

import com.tecnolpet.ot.model.TareaDetalleNotaPedido;

public class UploaderDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String answer;
	private String name;
	private String nameR;
	
	
	public String getNameR() {
		return nameR;
	}

	public void setNameR(String nameR) {
		this.nameR = nameR;
	}

	private TareaDetalleNotaPedido tarea;
	
	public TareaDetalleNotaPedido getTarea() {
		return tarea;
	}

	public void setTarea(TareaDetalleNotaPedido tarea) {
		this.tarea = tarea;
	}

	public UploaderDto(){}
	
	public UploaderDto(String answer ){
		this.answer=answer;				
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
