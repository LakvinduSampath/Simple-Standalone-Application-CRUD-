package modal.tableModal;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
@ToString
@Data
@NoArgsConstructor
public class TableModal {

    private String id;
    private String name;
    private Date dob;
    private String address;
    private Double salary;
    private String city;
    private String province;

    public TableModal(String id,String title, String name, Date dob, String address, Double salary, String city, String province,String postalcode) {
        this.id = id;
        this.name = title+". "+name;
        this.dob = dob;
        this.address = address;
        this.salary = salary;
        this.city = city+" - "+postalcode;
        this.province = province;
    }
}
