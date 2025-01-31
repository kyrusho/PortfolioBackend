package org.nabihi.haithamportfolio.comment.presentationlayer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequestModel {
    private String author;
    private String content;
    private LocalDateTime dateSubmitted; // Will be set in the service layer
}
