package org.nabihi.haithamportfolio.comment.businesslayer;

import org.nabihi.haithamportfolio.comment.presentationlayer.CommentRequestModel;
import org.nabihi.haithamportfolio.comment.presentationlayer.CommentResponseModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CommentService {
    Flux<CommentResponseModel> getApprovedComments();
    Flux<CommentResponseModel> getUnapprovedComments();
    Mono<CommentResponseModel> approveComment(String commentId);
    Mono<CommentResponseModel> addComment(Mono<CommentRequestModel> commentRequestModel);
    Mono<Void> deleteReview(String reviewId);



}
