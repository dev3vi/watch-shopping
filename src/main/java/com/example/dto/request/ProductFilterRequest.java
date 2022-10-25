package com.example.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductFilterRequest extends BaseRequest{
    private String brand;
    private String gender;
}
