package dev.erik.twitter_simplificado.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_roles")
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long roleId;

  private String name;

  public enum RoleName {
    ROLE_USER(2L), ROLE_ADMIN(1L);

    private final long roleId;

    RoleName(long roleId) {
      this.roleId = roleId;
    }

    public long getRoleId() {
      return roleId;
    }
  }
}
