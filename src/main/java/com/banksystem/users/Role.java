package com.banksystem.users;

import com.banksystem.core.AbstractEntity;
import com.banksystem.core.RoleName;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "roles")
public class Role extends AbstractEntity {

  @Enumerated(EnumType.STRING)
  @Column(name = "name", nullable = false)
  private RoleName name;

  public Role(RoleName name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}
