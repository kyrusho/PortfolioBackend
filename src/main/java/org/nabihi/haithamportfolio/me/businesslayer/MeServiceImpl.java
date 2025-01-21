package org.nabihi.haithamportfolio.me.businesslayer;

import org.nabihi.haithamportfolio.me.datalayer.MeRepository;
import org.nabihi.haithamportfolio.me.presentationlayer.MeResponseModel;
import org.nabihi.haithamportfolio.me.utils.EntityDTOUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class MeServiceImpl implements MeService{
    private final MeRepository meRepository;
    public MeServiceImpl(MeRepository meRepository) {
        this.meRepository = meRepository;
    }

    public Flux<MeResponseModel> getHaitham(){
        return meRepository.findAll()
                .map(EntityDTOUtil::toMeReponseModel);
    }

}
