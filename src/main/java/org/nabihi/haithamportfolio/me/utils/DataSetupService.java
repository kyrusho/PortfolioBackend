package org.nabihi.haithamportfolio.me.utils;

import lombok.RequiredArgsConstructor;
import org.nabihi.haithamportfolio.me.datalayer.Me;
import org.nabihi.haithamportfolio.me.datalayer.MeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DataSetupService implements CommandLineRunner {
    private final MeRepository meRepository;
    
    @Override
    public void run(String... args) throws Exception{
        setupMe();
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
}
