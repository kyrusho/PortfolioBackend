package org.nabihi.haithamportfolio.projects.businesslayer;

import org.nabihi.haithamportfolio.projects.datalayer.Project;
import org.nabihi.haithamportfolio.projects.presentationlayer.ProjectRequestModel;
import org.nabihi.haithamportfolio.projects.presentationlayer.ProjectResponseModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProjectService {
    Flux<ProjectResponseModel> getAllProjects();
    Mono<ProjectResponseModel> AddProject(Mono<ProjectRequestModel> projectRequestModel);
    Mono<ProjectResponseModel> EditProject(Mono<ProjectRequestModel> projectRequestModel, String projectId);

    Mono<ProjectResponseModel> GetProject(String projectId);
}
