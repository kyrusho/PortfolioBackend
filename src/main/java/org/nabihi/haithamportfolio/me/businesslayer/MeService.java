package org.nabihi.haithamportfolio.me.businesslayer;

import org.nabihi.haithamportfolio.me.presentationlayer.MeResponseModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MeService {
    Flux<MeResponseModel> getHaitham();
}
