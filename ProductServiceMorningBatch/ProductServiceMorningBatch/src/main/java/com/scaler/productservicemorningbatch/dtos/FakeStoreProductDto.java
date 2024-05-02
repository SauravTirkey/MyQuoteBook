package com.scaler.productservicemorningbatch.dtos;

import com.scaler.productservicemorningbatch.modles.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FakeStoreProductDto {
    private Long id;
    private String title;
    private Double price;
    private String category;
    private String description;
    private String image;
}
