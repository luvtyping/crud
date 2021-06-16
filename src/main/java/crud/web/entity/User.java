package crud.web.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;

    @NotEmpty(message = "Empty name")
    private String name;

    @NotEmpty(message = "Empty surname")
    private String surname;

    @Min(value = 0, message = "Age should be greater than 0")
//    @Pattern(regexp = "^[0-9]+$", message = "Should be only digits greater than 0")
    @NotNull
//    @Size(min = 1, max = 3)
    private int age;

    @Pattern(regexp = ".+((@mail.ru)|(gmail.com))", message = "Unacceptable Email - either: @mail.ru or gmail.com")
    private String email;
}
