package net.springsecurity.SpringSecurityRoleBasedAuth.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserInfo {

   @Column(name = "user_id")
   @Id
   @GeneratedValue(strategy = GenerationType.UUID)
   private String id;

   @Column(name = "user_name")
   private String username;

   @Column(name = "user_password")
   private String password;

   @Column(name = "user_roles")
   private String roles;
}
