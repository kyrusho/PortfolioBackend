package org.nabihi.haithamportfolio.comment.presentationlayer;

import org.nabihi.haithamportfolio.comment.businesslayer.CommentService;
import org.nabihi.haithamportfolio.comment.datalayer.Comment;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/comments")
@Validated
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"}, allowedHeaders = "content-Type", allowCredentials = "true")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("")
    public Flux<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @PostMapping("")
    public Mono<CommentResponseModel> addComment(@RequestBody CommentRequestModel commentRequestModel) {
        return commentService.addComment(Mono.just(commentRequestModel));
    }
}
