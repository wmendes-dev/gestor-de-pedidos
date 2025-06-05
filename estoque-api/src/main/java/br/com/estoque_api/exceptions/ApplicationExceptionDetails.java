package br.com.estoque_api.exceptions;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@SuperBuilder
public class ApplicationExceptionDetails {

    private String title;

    private int status;

    private List<String> details;

    private String developerMessage;

    private LocalDateTime timestamp;

}
