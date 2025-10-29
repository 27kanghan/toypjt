package com.example.toypjt.jwt;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

public class JwtAuthenticationFilter implements Filter {

    private final JwtTokenProvider jweTokenProvider;

    @Override
    public boolean isLoggable(LogRecord record) {
        return false;
    }
}
