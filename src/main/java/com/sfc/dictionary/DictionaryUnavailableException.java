package com.sfc.dictionary;

public class DictionaryUnavailableException extends RuntimeException {

    public DictionaryUnavailableException(String message) {
        super(message);
    }

    public DictionaryUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
