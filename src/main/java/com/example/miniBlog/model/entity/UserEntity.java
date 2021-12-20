package com.example.miniBlog.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Data
@Entity
@AllArgsConstructor
@SuperBuilder
@Table(name = "USER")
public class UserEntity extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @OneToMany
    @JoinTable(name = "USER_ROLE")
    private Set<RoleEntity> roleSet;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "author")
    private List<PostEntity> postEntities;

    public void addToRoleSet(RoleEntity roleEntity) {
        if (roleSet == null) {
            roleSet = new HashSet<>();
        }
        roleSet.add(roleEntity);
    }
}
