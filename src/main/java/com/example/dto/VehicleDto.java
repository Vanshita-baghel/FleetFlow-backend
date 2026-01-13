package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto {

    private Long vehicleId;

    private String vehicleNumber;

    private String type;    //mini truck, truck, container truck

}
