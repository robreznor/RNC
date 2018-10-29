package cl.minsal.api.object;

import java.util.ArrayList;
import java.util.List;

public class ValidationMessages {
	
	private Boolean validation;
	private List<String> messages;
	
	public ValidationMessages(){
		messages = new ArrayList<String>();
		this.validation = true;
	}
	
	public Boolean getValidation() {
		return validation;
	}
	
	public void setValidation(Boolean status) {
		this.validation = status;
	}

	public List<String> getMessages() {
		return messages;
	}
	
	public void setMessage(String message) {
		this.messages.add(message);
	}
	
}
