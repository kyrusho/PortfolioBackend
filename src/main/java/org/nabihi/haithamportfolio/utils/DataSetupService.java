package org.nabihi.haithamportfolio.utils;

import lombok.RequiredArgsConstructor;
import org.nabihi.haithamportfolio.comment.datalayer.Comment;
import org.nabihi.haithamportfolio.comment.datalayer.CommentRepository;
import org.nabihi.haithamportfolio.me.datalayer.Me;
import org.nabihi.haithamportfolio.me.datalayer.MeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataSetupService implements CommandLineRunner {
    private final MeRepository meRepository;
    private final CommentRepository commentRepository;
    
    @Override
    public void run(String... args) throws Exception{
        setupMe();
        setupComments();
    }

    private Me buildMe(String meId, String firstname, String lastname, int age, String origins) {
        Me me = new Me();
        me.setMeId(meId);
        me.setFirstname(firstname);
        me.setLastname(lastname);
        me.setAge(age);
        me.setOrigins(origins);
        return me;
    }

    private void setupMe() {
        List<Me> me = List.of(
               buildMe("002", "Haitham", "Nabihi", 20, "Moroccan-Canadian")
        );

        Flux.fromIterable(me)
                .flatMap(me1 -> meRepository.findMeByMeId(me1.getMeId())
                        .switchIfEmpty(meRepository.insert(me1)))
                .subscribe();
    }

    private void setupComments() {
        List<Comment> comments = List.of(
                new Comment( "01","1",  "John Doe", "Great portfolio!", LocalDateTime.now()),
                new Comment( "02", "2", "Jane Smith", "Impressive work!", LocalDateTime.now())
        );

        Flux.fromIterable(comments)
                .flatMap(commentRepository::save)
                .subscribe();
    }

}
