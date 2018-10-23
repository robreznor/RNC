package cl.minsal.api.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import cl.minsal.api.object.FileBucket;
 
@Component
public class FileValidator implements Validator {
     
    public boolean supports(Class<?> clazz) {
        return FileBucket.class.isAssignableFrom(clazz);
    }
 
    public void validate(Object obj, Errors errors) {
        FileBucket file = (FileBucket) obj;
                if(file.getFile()!=null){
            if (file.getFile().getSize() == 0) {
                errors.rejectValue("file", "missing.file");
            }
            if(!(file.getFile().getContentType().equals("text/csv") || file.getFile().getContentType().equals("application/vnd.ms-excel"))){
            	errors.rejectValue("file", "csv file type is only supported");
            } 
        }
    }
}