package com.gdaniel.glpayroll.admin.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.CascadeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.gdaniel.glpayroll.admin.role.entity.RoleEntity;
import com.gdaniel.glpayroll.user.employee.entitiy.EmployeeEntity;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbla9f232a1034d4b02b0efdaa295c2bd37")
public class UserEntity implements UserDetails {

    @Id // Specifies the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "LONG") // Configures the primary
    @Column(name = "colc95ed5740f41457fb6fd5158c133343d", nullable = false, updatable = false)
    private Long id;

    @Column(name = "cola9f22c10a045488c8b6ec289ad46f55a", unique = true)
    private String userName;

    @OneToOne
    @JoinColumn(name = "col4295b1e1b2f64aeb94a65790155834df", nullable = false)
    private EmployeeEntity employee;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "tblk6dbf67825b01405cb4790d4c3bbdec8d", // Name of the join table in the DB
            joinColumns = @JoinColumn(name = "colc95ed5740f41457fb6fd5158c133343d"), // Column for the User ID
            inverseJoinColumns = @JoinColumn(name = "col2b9e79e605924d4fa08d2b5135549e6c")) // Column for the Role ID
    private Set<RoleEntity> roles = new HashSet<>();

    @Column(name = "colde08b096ba774084846148ffb591d7b9", length = 128)
    private String storedHash;

    @Column(name = "col2748f249a6814344bcf308aeaf8352fd", length = 256)
    private String storedSalt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return this.roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toList());

    }

    @Override
    public @Nullable String getPassword() {
        return storedHash;
    }

    @Override
    public String getUsername() {
        return userName;
    }

}
