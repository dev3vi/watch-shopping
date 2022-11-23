package com.example.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseRequest {
    private Integer page = 1;
    private String sortBy;
    private String sortType;

    public String getSortType() {
        if (this.sortType == null || this.sortType.equals("")) {
            return "ASC";
        }
        return this.sortType;
    }
}
