package com.lawencon.community.dto.email;

import java.util.Map;

import lombok.Data;

@Data
public class EmailTemplate {

	private String to;
    private String from;
    private String subject;
    private String content;
    private Map<String, Object> model;
}