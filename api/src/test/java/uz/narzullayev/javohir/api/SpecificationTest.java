package uz.narzullayev.javohir.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uz.narzullayev.javohir.api.domain.Application;
import uz.narzullayev.javohir.api.repo.ApplicationMonitoringRepository;
import uz.narzullayev.javohir.dto.SearchCriteria;
import uz.narzullayev.javohir.spec.SearchSpecification;

import java.util.List;

@SpringBootTest
public class SpecificationTest {
    @Autowired
    private ApplicationMonitoringRepository applicationMonitoringRepository;

    @Test
    public  void test(){
        List<SearchCriteria> criteria = List.of(
                new SearchCriteria("id","=",646652)
        );
        var conditions = SearchSpecification.type(Application.class)
                        .conditions(criteria);

        List<Application> all = applicationMonitoringRepository.findAll(conditions);
        all.forEach(System.out::println);
    }
}
