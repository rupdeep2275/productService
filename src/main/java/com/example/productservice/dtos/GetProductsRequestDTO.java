package com.example.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetProductsRequestDTO {
    private int numberOfProducts;
    private int offset;
}
