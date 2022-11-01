package com.banksystem.core;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@MappedSuperclass
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity implements Serializable {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  @Column(name = "id", nullable = false, updatable = false, length = 36)
  private String id;

  protected AbstractEntity(String id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (isNull(other) || !(getClass().isInstance(other))) {
      return false;
    }
    var entity = (AbstractEntity) other;
    return nonNull(id) && id.equals(entity.getId());
  }

  @Override
  public int hashCode() {
    return nonNull(id) ? Objects.hashCode(id) : 31;
  }
}