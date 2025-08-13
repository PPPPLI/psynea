package com.cloud.psynea.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@SuppressWarnings("unused")
public class User{

    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private String username;
    private String password;
    private String email;
    private String tel;
    private Boolean newUser;
    private List<String> authorities;
    private Boolean isAccountNonExpired;
    private Boolean isAccountNonLocked;
    private Boolean isCredentialsNonExpired;
    private Boolean isEnabled;

    public User(String username, String passwd) {

        this.username = username;
        this.password = passwd;
    }
}
