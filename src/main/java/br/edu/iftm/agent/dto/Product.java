package br.edu.iftm.agent.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String name;
    private String category;
    private String description;
    private String color;
    private String size;
    private String gender;
    private double price;
    private String link;
}
