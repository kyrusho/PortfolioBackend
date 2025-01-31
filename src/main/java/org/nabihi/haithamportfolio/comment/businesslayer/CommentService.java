package org.nabihi.haithamportfolio.comment.businesslayer;

import org.nabihi.haithamportfolio.comment.datalayer.Comment;
import org.nabihi.haithamportfolio.comment.presentationlayer.CommentRequestModel;
import org.nabihi.haithamportfolio.comment.presentationlayer.CommentResponseModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CommentService {
    Flux<Comment> getAllComments();
    Mono<CommentResponseModel> addComment(Mono<CommentRequestModel> commentRequestModel);
}
