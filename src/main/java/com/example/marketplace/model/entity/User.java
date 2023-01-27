package com.example.marketplace.model.entity;

import com.example.marketplace.annotation.annotation.PhoneNumber;
import com.example.marketplace.model.entity.base.BaseEntity;
import com.example.marketplace.model.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static com.example.marketplace.constants.swagger.UserSwaggerConstants.*;
import static com.example.marketplace.constants.validation.UserValidationConstants.USER_PASSWORD_REQUIRED_MESSAGE;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity implements UserDetails {
   @Schema(description = USER_PHONE_NUMBER_DESCRIPTION,example = USER_PHONE_NUMBER_EXAMPLE)
   private String phoneNumber;
   @Schema(description = USER_FIRSTNAME_DESCRIPTION,example = USER_FIRSTNAME_EXAMPLE)
   private String firstName;
   @Schema(description = USER_LASTNAME_DESCRIPTION,example = USER_LASTNAME_EXAMPLE)
   private String lastName;
   @Schema(description = USER_PASSWORD_DESCRIPTION,example = USER_PASSWORD_EXAMPLE)
   private String password;

  @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
  private Bucket bucket;
  @OneToMany(
          mappedBy = "user",
          cascade = CascadeType.ALL,fetch = FetchType.LAZY)
  private List<Order>orders;
   private boolean enabled;
   @ElementCollection(targetClass = Role.class,fetch = FetchType.EAGER)
   @CollectionTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id"))
   @Enumerated(EnumType.STRING)
   private Set<Role> roles;


   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return getRoles();
   }

   @Override
   public String getUsername() {
      return phoneNumber;
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }
}
