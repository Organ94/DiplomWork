package com.example.diplomwork.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileRS {

    private String filename;
    private Long size;
}
