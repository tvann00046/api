package com.ra.base_spring_boot.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScreenRequest {

    @NotBlank(message = "Tên phòng chiếu không được để trống")
    private String name;

    @NotNull(message = "Số ghế không được để trống")
    @Min(value = 0, message = "Số ghế phải >= 0")
    private Integer seatCapacity;

    @NotNull(message = "theater không được để trống")
    private Integer theater;
}
