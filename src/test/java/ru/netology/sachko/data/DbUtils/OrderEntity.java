package ru.netology.sachko.data.DbUtils;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderEntity {
    private String  id;
    private String created;
    private String  creditId;
    private String  paymentId;

}
