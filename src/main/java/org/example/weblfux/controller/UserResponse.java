package org.example.weblfux.controller;

import lombok.Builder;
import lombok.Data;
import org.example.weblfux.model.User;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String createAt;
    private String updateAt;

    public static UserResponse fromUser(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .createAt(user.getCreateAt().toString())
                .updateAt(user.getUpdateAt().toString())
                .build();
    }
}
