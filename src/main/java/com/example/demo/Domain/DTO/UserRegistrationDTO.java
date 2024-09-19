package com.example.demo.Domain.DTO;

import com.example.demo.Domain.DatabaseEntity.User;
import com.example.demo.Domain.DatabaseEntity.WorkerType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDTO {
    @NotBlank(message = "name is mandatory")
    @Length(max = 30,message = "NameMax30")
    private String name;

    @NotBlank(message = "lastname is mandatory")
    @Length(max = 30,message = "LastNameMax30")
    private String lastname;

    @NotBlank(message = "Email is mandatory")
    @Length(max = 100,message = "EmailMax100")
    private String email;

    @NotBlank(message = "phoneNumber is mandatory")
    @Length(max = 30,message = "PhoneNumberMax100")
    private String phoneNumber;

    @NotBlank(message = "password is mandatory")
    @Length(max = 30,message = "passwordMax100")
    @Length(min = 6,message = "passwordMin100")
    private String password;

    @Min(value = 0,message = "MinValueForWorkerTypeIs0")
    private Integer workerType;


    public User DtoToUser(byte[] hash, byte[] salt, WorkerType workerType){
        return new User(this.name,this.lastname,this.email,hash,salt,this.phoneNumber,workerType,false);
    }
}
