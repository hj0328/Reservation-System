package kr.or.connect.reservation.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserRequest {
    @NotBlank
    private String name;

    // @Email이 null을 허용하기 때문에 @NotBlank
    @NotBlank @Email
    private String email;

    @NotBlank
    private String password;
}
