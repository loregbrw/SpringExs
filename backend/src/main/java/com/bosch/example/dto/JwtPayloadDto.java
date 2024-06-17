package com.bosch.example.dto;

public record JwtPayloadDto(String sub, String iss, Long iat, Long exp) { }
