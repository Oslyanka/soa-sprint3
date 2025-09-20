package com.challenge.soa.exception;

import java.time.Instant;
import java.util.List;

public class ApiError {
    public Instant timestamp = Instant.now();
    public int status;
    public String error;
    public String message;
    public String path;
    public List<FieldErrorItem> fieldErrors;

    public static class FieldErrorItem {
        public String field;
        public String message;
        public FieldErrorItem(String field, String message) { this.field = field; this.message = message; }
    }
}
