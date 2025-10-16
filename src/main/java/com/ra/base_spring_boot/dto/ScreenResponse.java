package com.ra.base_spring_boot.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScreenResponse {
    private Integer id;
    private String name;
    private Integer seatCapacity;
    private Integer theater;
    private String theaterName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    
}
