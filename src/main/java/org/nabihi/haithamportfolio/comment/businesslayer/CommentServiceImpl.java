package org.nabihi.haithamportfolio.comment.businesslayer;

import lombok.extern.slf4j.Slf4j;
import org.nabihi.haithamportfolio.comment.datalayer.Comment;
import org.nabihi.haithamportfolio.comment.datalayer.CommentRepository;
import org.nabihi.haithamportfolio.comment.presentationlayer.CommentRequestModel;
import org.nabihi.haithamportfolio.comment.presentationlayer.CommentResponseModel;
import org.nabihi.haithamportfolio.utils.EntityDTOUtil;
import org.nabihi.haithamportfolio.utils.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Flux<CommentResponseModel> getApprovedComments() {
        return commentRepository.findAll()
                .filter(Comment::isApproved)
                .map(EntityDTOUtil::toCommentResponseModel);
    }

    @Override
    public Flux<CommentResponseModel> getUnapprovedComments() {
        return commentRepository.findAll()
                .filter(comment -> !comment.isApproved())
                .map(EntityDTOUtil::toCommentResponseModel);
    }


    @Override
    public Mono<CommentResponseModel> approveComment(String commentId) {
        return commentRepository.findByCommentId(commentId)
                .flatMap(comment -> {
                    comment.setApproved(true);
                    return commentRepository.save(comment);
                })
                .map(EntityDTOUtil::toCommentResponseModel);
    }



    @Override
    public Mono<CommentResponseModel> addComment(Mono<CommentRequestModel> commentRequestModel) {
        return commentRequestModel
                .map(request -> Comment.builder()
                        .commentId(null) // MongoDB will auto-generate it
                        .author(request.getAuthor())
                        .content(request.getContent())
                        .dateSubmitted(LocalDateTime.now())
                        .approved(false) // Default to unapproved
                        .build()
                )
                .flatMap(commentRepository::insert) // Use insert so MongoDB assigns an ID
                .map(EntityDTOUtil::toCommentResponseModel);
    }

    @Override
    public Mono<Void> deleteReview(String reviewId) {
        return commentRepository.findByCommentId(reviewId)
                .switchIfEmpty(Mono.error(new NotFoundException("Re with ID '" + reviewId + "' not found.")))
                .flatMap(commentRepository::delete);
    }



}
