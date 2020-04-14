package cabal.desafio.domain;

import java.util.Date;

public class VoRetornoComercio {
	
	private String message;
	
	private Date timestamp;
	
	public VoRetornoComercio() {
		timestamp = new Date();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}	
	
}
