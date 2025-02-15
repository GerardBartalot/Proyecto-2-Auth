package com.example.Auth.Pr2.commons.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    @NonNull
    private String username;
    @NonNull
    private String password;

    private String name;
    private String email;

}
