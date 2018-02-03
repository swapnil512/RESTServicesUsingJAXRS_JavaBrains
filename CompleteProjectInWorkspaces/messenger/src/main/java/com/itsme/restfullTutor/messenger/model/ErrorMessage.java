package com.itsme.restfullTutor.messenger.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {

	private String errorMessage;
	private int errorCode;
	private String errorDocument;

	public ErrorMessage() {
	}

	public ErrorMessage(String errorMessage, int errorCode, String errorDocument) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.errorDocument = errorDocument;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDocument() {
		return errorDocument;
	}

	public void setErrorDocument(String errorDocument) {
		this.errorDocument = errorDocument;
	}

}
