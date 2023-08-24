package uz.narzullayev.javohir.util;

import lombok.experimental.UtilityClass;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import uz.narzullayev.javohir.dto.PageableRequest;
import uz.narzullayev.javohir.dto.SortDto;

import java.util.List;

@UtilityClass
public class PageableRequestUtil {
    public static Pageable toPageable(PageableRequest pageable) {
        List<SortDto> sortDto = pageable.getSort();
        return PageRequest.of(
                pageable.getPage(),
                pageable.getPerPage(),
                Sort.by(sorts(sortDto))
        );
    }

    public List<Sort.Order> sorts(List<SortDto> sorts) {
        return sorts.stream()
                .map(dto -> Sort.Order.by(dto.getName()).with(Sort.Direction.fromString(dto.getDirection())))
                .toList();
    }
}
