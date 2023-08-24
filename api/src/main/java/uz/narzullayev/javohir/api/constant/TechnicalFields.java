package uz.narzullayev.javohir.api.constant;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@MappedSuperclass
public abstract class TechnicalFields {

    @Column(name = "status",columnDefinition = "varchar(20) default 'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "deleted_at", columnDefinition = "timestamp without time zone")
    private Date deletedAt;

    //o'chirayotganda yoki biror o'zgartirish bo'lganda sabab yozib qo'yish uchun
    private String message;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date updatedAt;

    @CreatedBy
    @Column(name = "created_by_id", updatable = false)
    private Integer createdById;

    @LastModifiedBy
    @Column(name = "updated_by_id")
    private Integer updatedById;
}