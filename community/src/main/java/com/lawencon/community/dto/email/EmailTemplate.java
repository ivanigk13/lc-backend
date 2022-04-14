package com.lawencon.community.dto.email;

import java.util.Map;

import lombok.Data;

@Data
public class EmailTemplate {

	String to;
    String from;
    String subject;
    String content;
    private Map<String, Object> model;
}