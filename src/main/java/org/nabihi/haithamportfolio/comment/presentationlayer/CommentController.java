package org.nabihi.haithamportfolio.comment.presentationlayer;

import org.nabihi.haithamportfolio.comment.businesslayer.CommentService;
import org.nabihi.haithamportfolio.utils.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/comments")
@Validated
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"},
        allowedHeaders = "Content-Type",
        allowCredentials = "true")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/approved")
    public Flux<CommentResponseModel> getApprovedComments() {
        return commentService.getApprovedComments();
    }

    @GetMapping("/unapproved")
    public Flux<CommentResponseModel> getUnapprovedComments() {
        return commentService.getUnapprovedComments();
    }

    @PutMapping("/{commentId}/approve")
    public Mono<CommentResponseModel> approveComment(@PathVariable String commentId) {
        return commentService.approveComment(commentId);
    }

    @PostMapping("")
    public Mono<CommentResponseModel> addComment(@RequestBody Mono<CommentRequestModel> commentRequestModel) {
        return commentService.addComment(commentRequestModel);
    }

    @DeleteMapping("/{commentId}")
    public Mono<ResponseEntity<Void>> deleteReview(@PathVariable String commentId) {
        return commentService.deleteReview(commentId)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
                .onErrorResume(NotFoundException.class, e -> Mono.just(new ResponseEntity<Void>(HttpStatus.NOT_FOUND)));
    }
}
