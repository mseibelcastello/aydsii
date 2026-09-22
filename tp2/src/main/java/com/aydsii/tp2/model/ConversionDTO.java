package com.aydsii.tp2.model;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor 
@AllArgsConstructor 
public class ConversionDTO {
    private double amount;
    private String base;
    private String date;
    private Map<String, Double> rates;

}
