package com.example.pc.repos;

import com.example.pc.domain.App;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppRepo extends CrudRepository<App, Long> {

    List<App> findByReason(String reason);

}
