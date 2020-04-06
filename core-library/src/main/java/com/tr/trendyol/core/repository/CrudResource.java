package com.tr.trendyol.core.repository;


import com.tr.trendyol.core.model.BaseEntity;
import com.tr.trendyol.core.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * Developed by Yakup Kaya (yakupkaya@gmail.com)
 */
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public abstract class CrudResource<
        ENTITY extends BaseEntity<ID>,
        ID extends Serializable,
        REPOSITORY extends BaseRepository<ENTITY, ID>,
        SERVICE extends BaseService<ENTITY, ID, REPOSITORY>
        > {

    protected SERVICE service;

    /**
     * DELETE  /{entityName}/:id -> delete the "id" entity.
     */
    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        log.debug("REST request to delete {} : {}", getEntityName(), id);
        ENTITY entity = service.get(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity Not Found"));
        service.remove(entity.getId());
        return ResponseEntity.ok().build();
    }

    protected abstract String getEntityName();

    /**
     * GET  /{entityName}/:id -> get the "id" entity.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ENTITY> get(@PathVariable ID id) {
        log.debug("REST request to get {} : {}", getEntityName(), id);
        Optional<ENTITY> expression = service.get(id);

        return expression.map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                .orElse(new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    /**
     * GET  /expressions -> get all the expressions.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ENTITY>> getAll(Pageable pageable) {
        log.debug("REST request to get a page of " + getEntityName());
        Page<ENTITY> page = service.findAll(pageable);
        return new ResponseEntity<>(page.getContent(), HttpStatus.OK);
    }

    /**
     * PUT  /{entityName} -> Updates an existing entity.
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ENTITY> update(@Valid @RequestBody ENTITY entity) throws URISyntaxException {
        log.debug("REST request to update {} : {}", getEntityName(), entity);
        if (entity.getId() == null) {
            return create(entity);
        }
        return service.get(entity.getId()).map(existing -> {
            entity.setCreatedAt(existing.getCreatedAt());
            ENTITY result = service.save(entity);
            return ResponseEntity.ok()
                    .body(result);
        }).orElseGet(() ->
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND)
        );
    }

    /**
     * POST  /{entityName} -> Create a new entity.
     */
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ENTITY> create(@Valid @RequestBody ENTITY entity) throws URISyntaxException {
        log.debug("REST request to save {} : {}", getEntityName(), entity);
        if (entity.getId() != null) {
            return ResponseEntity.badRequest().body(null);
        }
        ENTITY result = service.save(entity);
        return ResponseEntity.created(new URI(getBaseUrl() + "/" + result.getId()))
                .body(result);
    }

    protected abstract String getBaseUrl();

    @Autowired
    public void setService(SERVICE service) {
        this.service = service;
    }
}
