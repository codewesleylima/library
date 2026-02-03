package com.wzzy.library.domain.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBookCommand {
    private Long id;
    private String title;
    private String author;
    private String isbn;
}