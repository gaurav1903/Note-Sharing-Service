package com.example.RateLimiter.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;
@Data
public class NoteDTO {


    @NotBlank
    String data;
}
