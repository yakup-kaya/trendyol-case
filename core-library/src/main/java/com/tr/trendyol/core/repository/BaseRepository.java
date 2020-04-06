package com.tr.trendyol.core.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Developed by Yakup Kaya (yakupkaya@gmail.com)
 */
@NoRepositoryBean
public interface BaseRepository<ENTITY, ID extends Serializable> extends PagingAndSortingRepository<ENTITY, ID> {

    List<ENTITY> findAll();

    Optional<ENTITY> findOneById(ID id);

}
