package com.interview_task.textsearchservice.validator;

import com.interview_task.textsearchservice.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FilePathExistValidator implements ConstraintValidator<FileExist, String> {

    @Autowired
    private FileUtils fileUtils;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return fileUtils.fileExist(value);
    }
}
