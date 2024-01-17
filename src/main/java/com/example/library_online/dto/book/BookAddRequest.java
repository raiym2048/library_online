package com.example.library_online.dto.book;

import com.example.library_online.entities.Type;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookAddRequest {
    private String name;
    private String author_full_name;
    private String transcript;
    private Integer prize;
    private Integer age_access;
    private String type;
}
