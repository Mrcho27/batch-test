package com.example.batchtest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor
@ToString
public class AladinItemDto {
    private String title;
    private String link;
    private String author;
    private LocalDate pubDate;
    private String description;
    private String cover;
    private String isbn;
}
