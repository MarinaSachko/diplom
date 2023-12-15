package ru.netology.sachko.data.DbUtils;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreditRequestEntity {
    private String id;
    private String bankId;
    private String created;
    private String status;
}
