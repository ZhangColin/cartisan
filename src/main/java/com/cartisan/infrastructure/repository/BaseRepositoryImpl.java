package com.cartisan.infrastructure.repository;

import com.cartisan.infrastructure.domain.AggregateRoot;
import com.cartisan.infrastructure.domain.SoftDeleteEntity;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * @author zhangcolin
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class BaseRepositoryImpl<T extends AggregateRoot, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {
    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
    }

//    @Override
//    public void delete(T entity) {
//        Assert.notNull(entity, "The entity must not be null!");
//
//        if (SoftDeleteEntity.class.isAssignableFrom(entity.getClass())) {
//            SoftDeleteEntity softDeleteEntity = (SoftDeleteEntity) entity;
//            softDeleteEntity.setActive(false);
//            softDeleteEntity.setDeleted(true);
//            this.save(entity);
//        } else {
//            super.delete(entity);
//        }
//
//    }
}
