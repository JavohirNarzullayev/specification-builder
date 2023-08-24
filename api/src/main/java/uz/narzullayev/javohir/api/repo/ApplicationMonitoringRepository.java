package uz.narzullayev.javohir.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.narzullayev.javohir.api.domain.Application;
@Repository
public interface ApplicationMonitoringRepository extends JpaRepository<Application, Long>, JpaGenericRepository<Application> {
}
