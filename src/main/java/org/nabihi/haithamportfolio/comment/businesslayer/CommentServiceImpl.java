package org.nabihi.haithamportfolio.comment.businesslayer;

import lombok.extern.slf4j.Slf4j;
import org.nabihi.haithamportfolio.comment.datalayer.Comment;
import org.nabihi.haithamportfolio.comment.datalayer.CommentRepository;
import org.nabihi.haithamportfolio.comment.presentationlayer.CommentRequestModel;
import org.nabihi.haithamportfolio.comment.presentationlayer.CommentResponseModel;
import org.nabihi.haithamportfolio.utils.EntityDTOUtil;
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
    public Flux<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Mono<CommentResponseModel> addComment(Mono<CommentRequestModel> commentRequestModel) {
        return commentRequestModel
                .map(request -> {
                    request.setDateSubmitted(LocalDateTime.now()); // Set the submission date
                    return EntityDTOUtil.toCommentEntity(request); // Convert request to entity
                })
                .flatMap(commentRepository::insert) // Save the comment in the repository
                .flatMap(savedComment -> commentRepository.findById(savedComment.getId())) // Fetch the saved comment
                .map(EntityDTOUtil::toCommentResponseModel); // Map the entity to a response model
    }

}
