package uz.narzullayev.javohir.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.narzullayev.javohir.api.constant.SuccessDataIterable;
import uz.narzullayev.javohir.api.domain.Application;
import uz.narzullayev.javohir.api.repo.ApplicationMonitoringRepository;
import uz.narzullayev.javohir.dto.PageableRequest;
import uz.narzullayev.javohir.spec.SearchSpecification;
import uz.narzullayev.javohir.util.PageableRequestUtil;

import javax.validation.Valid;
@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/v1/application/application-monitoring")
public class ApplicationResource {
    private final ApplicationMonitoringRepository applicationMonitoringRepository;

    @PostMapping("/pageable")
    public SuccessDataIterable<Application> getList(
            @Valid @RequestBody PageableRequest request
    ) {
        log.debug("pageable request {}", request);
        var conditions = SearchSpecification.type(Application.class)
                .conditions(request.getSearch());
        var pageable = PageableRequestUtil.toPageable(request);
        var all = applicationMonitoringRepository.findAll(conditions, pageable);
        return new SuccessDataIterable<>(all);
    }


}
