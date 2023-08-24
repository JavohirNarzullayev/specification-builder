package uz.narzullayev.javohir.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.hibernate.annotations.Type;
import uz.narzullayev.javohir.SearchException;
import uz.narzullayev.javohir.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.function.BiFunction;
import java.util.function.Function;

@Getter
@Schema(name = "Type of search", description = """
        IF PARAM IS NUMBER NEED TO CHOOSE ex: 1,1L,\n
        IF PARAM IS STRING NEED TO CHOOSE ex:Javohir,\n
        IF PARAM IS LOCALDATE NEED TO CHOOSE ex:2020.05.14,\n
        IF PARAM IS LOCALDATE NEED TO CHOOSE ex:2020.05.14  15:15:15,\n
        IF PARAM IS DATE OLD API NEED TO CHOOSE ex:2020.05.14  15:15:15,\n
        IF PARAM IS JSON NEED TO CHOOSE ex:Javohir,
        IF PARAM IS BOOL NEED TO CHOOSE ex:true,false,
        """)
public enum TypeSearch {
    NUMBER(TypeSearch::isNumber),
    STRING(TypeSearch::isString),
    LOCALDATE(TypeSearch::isLocalDate),
    LOCALDATETIME(TypeSearch::isLocalDateTime),
    DATE(TypeSearch::isDate),
    JSON(TypeSearch::isJson),
    BOOL(TypeSearch::isBoolean);


    public final Function<Field, Boolean> typeFrom;

    TypeSearch(Function<Field, Boolean> typeFrom) {
        this.typeFrom = typeFrom;
    }

    private static boolean isLocalDate(Field field) {
        return LocalDate.class.isAssignableFrom(field.getType());
    }

    private static boolean isLocalDateTime(Field field) {
        return LocalDateTime.class.isAssignableFrom(field.getType());
    }

    private static boolean isNumber(Field field) {
        return ReflectionUtils.isNumber(field.getType());
    }

    private static boolean isString(Field field) {
        boolean isEnum = Enum.class.isAssignableFrom(field.getType());
        return isEnum || String.class.isAssignableFrom(field.getType());
    }


    private static boolean isDate(Field field) {
        return Date.class.isAssignableFrom(field.getType());
    }

    private static boolean isJson(Field field) {
        return ReflectionUtils.hasAnnotation(field, Type.class);
    }

    private static boolean isBoolean(Field field) {
        return ReflectionUtils.isBoolean(field.getType());
    }


    public static TypeSearch getType(Class<?> targetClass, String keys) {
        String[] split = keys.split("\\.");
        if (split.length == 0) {
            throw new SearchException("Params keys is empty");
        }
        Field field = ReflectionUtils.getField(targetClass, split[0]);
        if (field == null) {
            throw new SearchException(
                    "Field does not exist in class " + targetClass.getSimpleName() + " field " + split[0]
            );
        }
        return Arrays.stream(TypeSearch.values())
                .filter(typeSearch -> typeSearch.getTypeFrom().apply(field))
                .findFirst()
                .orElseThrow(() -> new SearchException("Type not found"));
    }


}
