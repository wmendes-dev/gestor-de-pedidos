package br.com.estoque_api.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FieldValidation {

    private String field;

    private String message;

}
