package uz.narzullayev.javohir.api.constant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.Assert;

import java.util.Locale;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Name {
    private String uz;
    private String oz;
    private String ru;
    private String en;

    @JsonIgnore
    public String getLocale() {
        Locale locale = LocaleContextHolder.getLocale();
        if (locale.equals(Locale.ENGLISH))
            return en;
        else if (locale.equals(Locale.forLanguageTag("uz"))) {
            return uz;
        } else if (locale.equals(Locale.forLanguageTag("oz"))) {
            return oz;
        } else
            return ru;
    }

    @JsonIgnore
    public void concatStarting(String txt) {
        Assert.notNull(txt,"Text of append first not should be null");
        this.uz = txt+": "+this.uz;
        this.ru = txt+": "+this.ru;
        this.en = txt+": "+this.en;
        this.oz = txt+": "+this.oz;
    }

    @JsonIgnore
    public String getLocale(String lang) {
        return switch (lang) {
            case "uz" -> uz;
            case "oz" -> oz;
            case "ru" -> ru;
            default -> en;
        };
    }
}
