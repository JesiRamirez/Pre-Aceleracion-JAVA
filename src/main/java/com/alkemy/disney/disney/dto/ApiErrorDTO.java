package com.alkemy.disney.disney.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ApiErrorDTO {

    private HttpStatus status;

    private String message;

    private List<String> errors;
}
