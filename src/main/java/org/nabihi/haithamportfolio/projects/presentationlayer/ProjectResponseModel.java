package org.nabihi.haithamportfolio.projects.presentationlayer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.nabihi.haithamportfolio.skill.datalayer.Skill;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProjectResponseModel {
    private String projectName;
    private String iconUrl;
    private String gitRepo;
    private List<Skill> skills;
}
