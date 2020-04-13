package cabal.desafio.domain;

import java.util.Date;

import com.sun.jmx.snmp.Timestamp;

public class VoRetornoComercio {
	
	private String message;
	
	private Date timestamp;
	
	public VoRetornoComercio() {
		timestamp = new Timestamp().getDate();
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
