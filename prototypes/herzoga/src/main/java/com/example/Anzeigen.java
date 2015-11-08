package com.example;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

public interface Anzeigen extends Repository<Article,Long>{

List<Article> findByName(String name);

List<Article> findBylocationZIP(String locationZIP);

void delete(long id);

Article save(Article entry);

Article findOne(Long id);

Iterable<Article> findAll();

long count();

}
