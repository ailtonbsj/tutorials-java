package io.bank.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {
    private Long id;
    private Double balance;
    private boolean active;
}
