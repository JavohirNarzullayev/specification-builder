package uz.narzullayev.javohir.api.domain;

import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import uz.narzullayev.javohir.api.constant.ApplicationType;
import uz.narzullayev.javohir.api.constant.InvoiceStatus;
import uz.narzullayev.javohir.api.constant.TechnicalFields;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "invoices", indexes = {
        @Index(name = "idx_invoice_application_id", columnList = "application_id")
})
@TypeDefs({
        @TypeDef(name = "string-array", typeClass = StringArrayType.class),
        @TypeDef(name = "int-array", typeClass = IntArrayType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Invoice extends TechnicalFields {

    @Id
    @SequenceGenerator(name = "invoices_id_seq", sequenceName = "invoices_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoices_id_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "request_id")
    private String requestId;

    @Column(name = "application_id",unique = true)
    private Long applicationId;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", insertable = false, updatable = false)
    private Application application;

    @Column(name = "service_id")
    private Integer serviceId;


    /*  payee       */
    @Column(name = "department_id")
    private Integer departmentId;

    @Column(name = "department_name")
    private String departmentName;

    /*  payer       */
    @Column(name = "payer")
    private String payer;//Наименование заявителя

    @Column(name = "payer_pin")
    private String payerPin;

    @Column(name = "payer_tin")
    private Integer payerTin;

    @Column(name = "payer_phone")
    private String payerPhone;

    @Column(name = "payer_id")
    private Long payerId;

    /*  Invoice     */
    @Column(name = "invoice_id")
    private Long invoiceId;

    @Column(name = "serial")
    private String serial;//Номер инвойса

    @Column(name = "amount", precision = 20, scale = 2)
    private Double amount;//Сумма платежа, fee

    @Column(name = "balance", precision = 20, scale = 2)
    private Double balance;//balance

    @Column(name = "amount_online", precision = 20, scale = 2)
    private Double amountOnline;//Сумма платежа, fee

    @Column(name = "mfo")
    private String mfo;

    @Column(name = "invoice_expired")
    private Boolean invoiceExpired;

    @Column(name = "invoice_expire_date")
    private Date invoiceExpireDate;

    @Column(name = "detail")
    private String detail;

    /*  Bank    */
    @Column(name = "bank_account")
    private String bankAccount;

    @Column(name = "budget_account")
    private String budgetAccount;

    @Column(name = "bank_mfo")
    private String bankMfo;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "bank_inn")
    private String bankInn;

    @Column(name = "bank_trans_id")
    private String bankTransId;

    @Column(name = "terminal_id")
    private String terminalId;

    @Column(name = "bank_bmm_number")
    private String bankBmmNumber;

    //todo fix regionId
    @Column(name = "region_id")
    private Long regionId;

    @Enumerated(EnumType.STRING)
    @Column(name = "application_type")
    private ApplicationType applicationType;

    @Enumerated(EnumType.STRING)
    private InvoiceStatus invoiceStatus;

    @Column(name = "paid_at", columnDefinition = "timestamp without time zone")
    private Date paidAt;
}

