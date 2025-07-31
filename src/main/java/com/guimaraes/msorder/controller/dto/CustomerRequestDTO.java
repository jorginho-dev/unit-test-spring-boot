package com.guimaraes.msorder.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record CustomerRequestDTO (@NotBlank String name){
}
