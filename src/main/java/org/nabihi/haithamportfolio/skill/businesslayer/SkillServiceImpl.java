package org.nabihi.haithamportfolio.skill.businesslayer;
import lombok.extern.slf4j.Slf4j;
import org.nabihi.haithamportfolio.skill.datalayer.SkillRepository;
import org.nabihi.haithamportfolio.skill.presentationlayer.SkillResponseModel;
import org.nabihi.haithamportfolio.utils.EntityDTOUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Slf4j
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public Flux<SkillResponseModel> getALlSkills() {
        return skillRepository.findAll().map(EntityDTOUtil::toSkillResponseModel);
    }
}