package com.gdaniel.glpayroll.admin.role.entity;

import java.util.HashSet;
import java.util.Set;

import com.gdaniel.glpayroll.admin.user.entity.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl0d2189587ccb42b0add466e1baacb334")
public class RoleEntity {

    @Id // Specifies the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "LONG") // Configures the primary
    @Column(name = "col2b9e79e605924d4fa08d2b5135549e6c")
    private Long roleId;

    @Column(name = "col774c4a5c9e24406b9570f26fdb2e0208", nullable = false, length = 45)
    private String roleName;

    @ManyToMany(mappedBy = "roles") // Indicates that the 'roles' field in User is the owning side
    private Set<UserEntity> users = new HashSet<>();

}
