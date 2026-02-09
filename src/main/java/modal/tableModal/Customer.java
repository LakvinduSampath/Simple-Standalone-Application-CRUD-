package modal.tableModal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private String id;
    private String title;
    private String name;
    private LocalDate dob;
    private String addrss;
    private Double salary;
    private String city;
    private String province;
    private String postalCode;
}
