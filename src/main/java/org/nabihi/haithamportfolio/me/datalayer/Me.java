package org.nabihi.haithamportfolio.me.datalayer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Me {
    @Id
    private String id;

    private String meId;
    private String firstname;
    private String lastname;
    private int age;
    private String origins;
}
