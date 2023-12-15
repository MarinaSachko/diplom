package ru.netology.sachko.data.DbUtils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntity {
    private String id;
    private String amount;
    private String created;
    private String status;
    private String transactions_id;
}
