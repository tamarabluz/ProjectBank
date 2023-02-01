package io.github.tamarabluz.mscreditappraiser.application.exception;

import lombok.Getter;

public class MicroservicesCommunicationErrorException extends Exception{
    @Getter
    private Integer status;
    public MicroservicesCommunicationErrorException(String msg, Integer status) {
        super(msg);
        this.status = status;
    }
}
