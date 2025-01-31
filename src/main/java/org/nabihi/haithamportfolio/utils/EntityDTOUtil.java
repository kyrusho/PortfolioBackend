package org.nabihi.haithamportfolio.utils;

import lombok.RequiredArgsConstructor;
import org.nabihi.haithamportfolio.comment.datalayer.Comment;
import org.nabihi.haithamportfolio.comment.presentationlayer.CommentRequestModel;
import org.nabihi.haithamportfolio.comment.presentationlayer.CommentResponseModel;
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

    public static Comment toCommentEntity(CommentRequestModel request) {
        return Comment.builder()
                .author(request.getAuthor())
                .content(request.getContent())
                .dateSubmitted(request.getDateSubmitted())
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

}
