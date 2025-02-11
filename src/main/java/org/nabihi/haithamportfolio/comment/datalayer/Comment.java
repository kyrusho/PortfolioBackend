package org.nabihi.haithamportfolio.comment.datalayer;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
public class Comment {
    @Id
    private String commentId;
    private String author;
    private String content;
    private LocalDateTime dateSubmitted;
    private boolean approved;
}
