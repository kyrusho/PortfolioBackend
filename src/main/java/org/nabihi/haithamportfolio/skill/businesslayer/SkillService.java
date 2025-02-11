package org.nabihi.haithamportfolio.skill.businesslayer;

import org.nabihi.haithamportfolio.skill.presentationlayer.SkillResponseModel;
import reactor.core.publisher.Flux;

public interface SkillService {

    Flux<SkillResponseModel> getALlSkills();
}