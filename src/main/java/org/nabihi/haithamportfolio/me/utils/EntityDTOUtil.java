package org.nabihi.haithamportfolio.me.utils;

import lombok.RequiredArgsConstructor;
import org.nabihi.haithamportfolio.me.datalayer.Me;
import org.nabihi.haithamportfolio.me.presentationlayer.MeResponseModel;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntityDTOUtil {

    public static MeResponseModel toMeReponseModel(Me me) {
        return MeResponseModel.builder()
                .meId(me.getMeId())
                .firstname(me.getFirstname())
                .lastname(me.getLastname())
                .age(me.getAge())
                .origins(me.getOrigins())
                .build();
    }
}
