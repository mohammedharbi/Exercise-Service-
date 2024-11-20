package com.example.article.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Article {

    //id:
    @NotEmpty(message = "ID is empty")
    @Size(min = 2, message = "ID size at least 2.")
    private String ID;

    @NotEmpty(message = "title is empty")
    @Size(max = 100,message = "maximum 100 letters only.")
    private String title;

    @NotEmpty(message = "author is empty")
    @Size(min = 4,max = 20,message = "author name must be at least min 4 and max 20 letters.")
    private String author;

    @NotEmpty(message = "content is empty")
    @Size(max = 200,message = "max 200 letters only for content")
    private String content;

    @NotEmpty(message = "category is empty")
    @Pattern(regexp = "^(technology|sports|politics)$")
    private String category;

    @NotEmpty(message = "image url is empty")
    private String imageurl;

    @AssertFalse
    private boolean isPublished;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publishDate;

}
