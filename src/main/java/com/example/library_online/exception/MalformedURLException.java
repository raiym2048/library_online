package com.example.library_online.exception;

import java.io.IOException;

public class MalformedURLException extends IOException {
    public MalformedURLException() {
    }

    /**
     * Constructs a {@code MalformedURLException} with the
     * specified detail message.
     *
     * @param   msg   the detail message.
     */
    public MalformedURLException(String msg) {
        super(msg);
    }

}
