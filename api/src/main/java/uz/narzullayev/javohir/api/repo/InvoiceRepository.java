package uz.narzullayev.javohir.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.narzullayev.javohir.api.constant.InvoiceStatus;
import uz.narzullayev.javohir.api.domain.Invoice;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>, JpaGenericRepository<Invoice> {
    Optional<Invoice> findBySerial(String serial);

    boolean existsByApplicationId(Long applicationId);

    Optional<Invoice> findByApplicationId(Long applicationId);

    List<Invoice> findByInvoiceStatus(InvoiceStatus invoiceStatus);

}
