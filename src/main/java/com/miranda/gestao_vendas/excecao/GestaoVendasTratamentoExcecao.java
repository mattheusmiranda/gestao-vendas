package com.miranda.gestao_vendas.excecao;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class GestaoVendasTratamentoExcecao extends ResponseEntityExceptionHandler {

    public static final String NOT_BLANK = "NotBlank";
    public static final String LENGTH = "Length";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<Erro> errors = gerarListaDeErros(ex.getBindingResult());
        return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request) {
        String mensagemUsuario = "Recurso não encontrado";
        String mensagemDesenvolvedor = ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<Object> handleRegraNegocioException(RegraNegocioException ex, WebRequest request) {
        String mensagemUsuario = ex.getMessage();
        String mensagemDesenvolvedor = ex.getMessage();
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        String mensagemUsuario = ex.getMessage();
        String mensagemDesenvolvedor = ex.getMessage();
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return handleExceptionInternal(ex, erros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private List<Erro> gerarListaDeErros(BindingResult bindingResult) {
        List<Erro> erros = new ArrayList<>();
        bindingResult.getAllErrors().forEach(error -> {
            String mensagemUsuario= tratarMensagemDeErroParaUsuario(error);
            String mensagemDesenvolvedor = error.toString();
            erros.add(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        });
        return erros;
    }

    private String tratarMensagemDeErroParaUsuario(ObjectError error) {
        if(error.getCode().equals(NOT_BLANK)) return error.getDefaultMessage().concat(" é obrigatório.");
        if(error.getCode().equals(LENGTH)) return error.getDefaultMessage()
                .concat(String.format(" deve ter entre %s e %s caracterres.", error.getArguments()[2], error.getArguments()[1]));
        return error.toString();
    }

}
