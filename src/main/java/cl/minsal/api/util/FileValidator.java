package cl.minsal.api.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import cl.minsal.api.object.FileBucket;
import cl.minsal.api.object.ValidationMessages;
 
@Component
public class FileValidator {
     
    public static ValidationMessages validate(Object obj) {
    	
    	ValidationMessages validadorResponse = new ValidationMessages();
        FileBucket file = (FileBucket) obj;
        if(file.getFile()!=null){
            if (file.getFile().getSize() == 0) {
            	validadorResponse.setTitle("No ha especificado el archivo");
            	validadorResponse.setValidation(false);
            	return validadorResponse;    
            }
            if(!(file.getFile().getContentType().equals("text/csv") || file.getFile().getContentType().equals("application/vnd.ms-excel"))){
            	validadorResponse.setTitle("El archivo debe estar en formato csv");
            	validadorResponse.setValidation(false);
            	return validadorResponse;
            } 
            return validadorResponse;
        }else {
        	validadorResponse.setValidation(false);
        	validadorResponse.setTitle("No se encuentra el archivo");
        	return validadorResponse;
        }
    }
}