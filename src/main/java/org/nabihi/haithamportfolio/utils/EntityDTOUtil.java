package org.nabihi.haithamportfolio.utils;

import lombok.RequiredArgsConstructor;
import org.nabihi.haithamportfolio.UserSubdomain.DataLayer.User;
import org.nabihi.haithamportfolio.UserSubdomain.PresentationLayer.UserResponseModel;
import org.nabihi.haithamportfolio.comment.datalayer.Comment;
import org.nabihi.haithamportfolio.comment.presentationlayer.CommentRequestModel;
import org.nabihi.haithamportfolio.comment.presentationlayer.CommentResponseModel;
import org.nabihi.haithamportfolio.me.datalayer.Me;
import org.nabihi.haithamportfolio.me.presentationlayer.MeResponseModel;
import org.nabihi.haithamportfolio.projects.datalayer.Project;
import org.nabihi.haithamportfolio.projects.presentationlayer.ProjectRequestModel;
import org.nabihi.haithamportfolio.projects.presentationlayer.ProjectResponseModel;
import org.nabihi.haithamportfolio.skill.datalayer.Skill;
import org.nabihi.haithamportfolio.skill.presentationlayer.SkillResponseModel;
import org.springframework.beans.BeanUtils;
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

    public static Comment toCommentEntity(CommentRequestModel request) {
        return Comment.builder()
                .author(request.getAuthor())
                .content(request.getContent())
                .build();
    }

    public static CommentResponseModel toCommentResponseModel(Comment comment) {
        return CommentResponseModel.builder()
                .commentId(comment.getCommentId())
                .author(comment.getAuthor())
                .content(comment.getContent())
                .dateSubmitted(comment.getDateSubmitted())
                .build();
    }

    // Convert SkillRequestModel DTO to Skill entity
    public static Project toProjectEntity(ProjectRequestModel request) {
        return Project.builder()
                .projectName(request.getProjectName())
                .iconUrl(request.getIconUrl())
                .gitRepo(request.getGitRepo())
                .skills(request.getSkills())
                .build();
    }

    public static SkillResponseModel toSkillResponseModel(Skill skill) {
        SkillResponseModel skillResponseModel  = new SkillResponseModel();
        BeanUtils.copyProperties(skill, skillResponseModel);
        return skillResponseModel;
    }

    public static ProjectResponseModel toProjectResponseModel(Project project) {
        ProjectResponseModel projectResponseModel  = new ProjectResponseModel();
        BeanUtils.copyProperties(project, projectResponseModel);
        return projectResponseModel;
    }

    public static UserResponseModel toUserResponseModel(User user) {
        UserResponseModel model = new UserResponseModel();
        model.setUserId(user.getUserId());
        model.setEmail(user.getEmail());
        model.setFirstName(user.getFirstName());
        model.setLastName(user.getLastName());
        model.setRoles(user.getRoles());
        model.setPermissions(user.getPermissions());
        return model;
    }
}
