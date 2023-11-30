package org.example.dto;

import org.example.entity.Address;

public record AddressDtor(String number , Long id) {
    public AddressDtor(String number, Long id) {
        this.number = number;
        this.id = id;
    }
}
