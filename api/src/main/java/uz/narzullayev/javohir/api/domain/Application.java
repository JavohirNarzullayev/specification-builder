package uz.narzullayev.javohir.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import uz.narzullayev.javohir.api.constant.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "applications")
@TypeDefs({
        @TypeDef(name = "string-array", typeClass = StringArrayType.class),
        @TypeDef(name = "int-array", typeClass = IntArrayType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Application extends TechnicalFields {
    @Id
    @SequenceGenerator(name = "applications_id_seq", sequenceName = "applications_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "applications_id_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "pin", length = 50)
    private String pin;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "changed_phone", length = 20)
    private String changedPhone;//Used when change number to new number

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ApplicationType type;

    @Column(name = "document_number")
    private String documentNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type")
    private DocumentType documentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "application_status")
    private ApplicationStatus applicationStatus;

    @Column(name = "register_id")
    private Long registerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "organization_type")
    private OrganizationType organizationType;

    @Column(name = "external_application_id")
    private String externalApplicationId;

    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "invoice_id")
    private Long invoiceId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id", insertable = false, updatable = false)
    @ToString.Exclude
    @JsonIgnore
    private Invoice invoice;

    @Formula("concat(first_name,' ',last_name,' ',middle_name)")
    private String fullName;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "organization_id")
    private Integer organizationId;

    @Type(type = "jsonb")
    @Column(name = "message_error", columnDefinition = "jsonb")
    private Name messageError;

    private Boolean licenseSend = false;//registration step when complete send to license
}
