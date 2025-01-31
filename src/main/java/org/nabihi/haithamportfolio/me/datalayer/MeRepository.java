package org.nabihi.haithamportfolio.me.datalayer;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface MeRepository extends ReactiveMongoRepository<Me, String> {
    Mono<Me> findMeByMeId(String meId);
}

