package org.j6d2.demoj6b2.bean;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @NotBlank(message = "Name is required")
    String name;

    @NotNull(message = "Gender is required")
    Boolean gender;

    @Min(value = 0, message = "Mark must be positive")
    Double mark;

    Contact contact;

    List<String> subjects;
}