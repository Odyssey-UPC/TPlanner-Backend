package com.upc.tplanner.TPlanner.advertising.dto;

import lombok.Data;

@Data
public class AdvertisingRequest {
    private String name;
    private String thumbnail;
    private String serviceName;
    private String redirectTo;
    private Double amountInvested;
}
