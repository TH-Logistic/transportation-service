package com.thlogistic.transportation.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissionDto {
    String _id;
    String name;
    String email;
    String birthday;
    String avatar;
    String role;
    String deletedAt;
}
