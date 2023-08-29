package net.javaguides.springboot.form.validator;


import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import net.javaguides.springboot.model.ServerWithoutAutostop;


public class ServerWithoutAutostopFormValidator implements Validator {

	//which objects can be validated by this validator
	@Override
	public boolean supports(Class<?> paramClass) {
		return ServerWithoutAutostop.class.equals(paramClass);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
		
		ServerWithoutAutostop server = (ServerWithoutAutostop) obj;
		if(server.getId() <=0){
			errors.rejectValue("id", "negativeValue", new Object[]{"'id'"}, "id can't be negative");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "requestedPerson", "requestedPerson.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "jiraTicket", "jiraTicket.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "teamName", "teamName.required");
	}
}
