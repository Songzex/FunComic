package com.scy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoenShoucan {

    private String userId;
    private String resourceId;
    private String type;
}
