package uz.narzullayev.javohir.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Pageable request payload")
public class PageableRequest {
    @Schema(name = "per_page",
            description = "На страницу",
            example = "10")
    private int perPage = 10;

    @Schema(description = "Страница", example = "1")
    private int page = 0;

    @Schema(description = "Сортировать данные")
    private List<SortDto> sort = new ArrayList<>();

    @Valid
    private List<SearchCriteria> search = new ArrayList<>();
}
