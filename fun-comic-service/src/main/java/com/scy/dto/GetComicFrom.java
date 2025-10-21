package com.scy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetComicFrom implements Serializable {
    private String  ComicId;
    private String Chapter;
}
