package com.example.demo;
import java.util.List;

import com.google.cloud.spring.data.datastore.repository.DatastoreRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface GameRepository extends PagingAndSortingRepository<Game, Long >, DatastoreRepository<Game, Long > {
    Page<Game> findAllByOrderByScoreDesc(Pageable pageable);
    Page<Game> findAllByUserIdOrderByScoreDesc(String userId, Pageable pageable);

    List<Game> findByUserId(String userId);
}