package uz.narzullayev.javohir.api;


import uz.narzullayev.javohir.api.domain.Invoice;
import uz.narzullayev.javohir.dto.TypeSearch;

public class TypeGetTest {
    public static void main(String[] args) {
        TypeSearch id = TypeSearch.getType(Invoice.class, "id");
        System.out.println(id);
        TypeSearch requestId = TypeSearch.getType(Invoice.class, "requestId");
        System.out.println(requestId);

        TypeSearch amount = TypeSearch.getType(Invoice.class, "amount");
        System.out.println(amount);

        TypeSearch invoiceExpired = TypeSearch.getType(Invoice.class, "invoiceExpired");
        System.out.println(invoiceExpired);

        TypeSearch invoiceStatus = TypeSearch.getType(Invoice.class, "invoiceStatus");
        System.out.println(invoiceStatus);
    }
}
