package com.tr.trendyol.core.service;

import com.tr.trendyol.core.repository.BaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Developed by Yakup Kaya (yakupkaya@gmail.com)
 */
@Slf4j
@Transactional
public abstract class BaseService<ENTITY, ID extends Serializable, REPOSITORY extends BaseRepository<ENTITY, ID>> {

    protected REPOSITORY repository;

    public BaseService(REPOSITORY rep) {
        repository = rep;
    }

    @Transactional(readOnly = true)
    public Page<ENTITY> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<ENTITY> get(ID id) {
        return repository.findOneById(id);
    }

    public void remove(ID entity) {
        repository.deleteById(entity);
    }

    public ENTITY save(ENTITY entity) {
        log.debug("Entity saved. Detail: " + entity);
        return repository.save(entity);
    }

    public List<ENTITY> save(List<ENTITY> entities) {
        List<ENTITY> target = new ArrayList<>();
        repository.saveAll(entities).forEach(target::add);
        return target;
    }

}
