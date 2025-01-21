package org.nabihi.haithamportfolio.me.presentationlayer;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class MeResponseModel {
    String meId;
    String firstname;
    String lastname;
    int age;
    String origins;
}
