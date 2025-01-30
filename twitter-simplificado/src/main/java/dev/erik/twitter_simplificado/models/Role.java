package dev.erik.twitter_simplificado.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb_roles")
@Getter
@Setter
@RequiredArgsConstructor
public class Role {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "role_id")
  private Long roleId;

  private String name;

  public enum Values {

    ADMIN(1L),
    BASIC(2L);

    long roleId;

    Values(long roleId) {
      this.roleId = roleId;
    }

    public long getRoleId() {
      return roleId;
    }
  }
}
