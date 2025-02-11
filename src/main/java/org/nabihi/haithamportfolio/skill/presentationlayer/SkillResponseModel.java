package org.nabihi.haithamportfolio.skill.presentationlayer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkillResponseModel {
    String skillId;
    String skillName;
    String skillLogo;
}