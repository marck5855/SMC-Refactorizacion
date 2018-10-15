package mx.com.tp.smc.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import mx.com.tp.smc.model.FileModel;

@Component
public class FileValidator implements Validator{

//	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return FileModel.class.isAssignableFrom(clazz);
	}

//	@Override
	public void validate(Object target, Errors errors) {
		System.out.println("ENTRE A VALIDAR");
		FileModel fileUpload = (FileModel) target;
		System.out.println("ENTRE A VALIDAR EL VALOR DE FILEUPLOAD ES -"+ fileUpload);
		CommonsMultipartFile[] commonsMultiFiles = fileUpload.getFiles();
		for(CommonsMultipartFile multipartFile: commonsMultiFiles){
			if(multipartFile.getSize() == 0){
				errors.rejectValue("files", "missing.file");
			}
		}
	}

}
