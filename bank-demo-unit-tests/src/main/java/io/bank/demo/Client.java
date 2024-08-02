package io.bank.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Client {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private boolean active;
    private Long accountId;
}
