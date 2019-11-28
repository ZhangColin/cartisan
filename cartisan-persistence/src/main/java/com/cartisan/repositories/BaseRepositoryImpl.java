package com.cartisan.repositories;

import com.cartisan.domains.AggregateRoot;
import com.cartisan.domains.SoftDeleteEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * @author colin
 */
@Slf4j
@Transactional(rollbackOn = Exception.class)
public class BaseRepositoryImpl<T extends AggregateRoot, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {
//    private final JpaEntityInformation<T, ?> entityInformation;
//    private final EntityManager entityManager;

//    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
//        super(entityInformation, entityManager);
//        this.entityInformation = entityInformation;
//        this.entityManager = entityManager;
//    }

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
//        this.entityManager = entityManager;
    }

//    @Override
//    @Transactional(rollbackOn = Exception.class)
//    public <S extends T> S save(S entity) {
//        if (this.getEntityInformation().isNew(entity)) {
//            entity.setCreateDateTime(new Timestamp(System.currentTimeMillis()));
//            this.getEntityManager().persist(entity);
//        } else {
//            entity.setUpdateDateTime(new Timestamp(System.currentTimeMillis()));
//            this.getEntityManager().merge(entity);
//        }
//        return entity;
//    }

    @Override
    public void delete(T entity) {
        Assert.notNull(entity, "The entity must not be null!");

        if (SoftDeleteEntity.class.isAssignableFrom(entity.getClass())) {
            SoftDeleteEntity softDeleteEntity = (SoftDeleteEntity) entity;
            softDeleteEntity.setActive(false);
            softDeleteEntity.setDeleted(true);
            this.save(entity);
        } else {
            super.delete(entity);
        }


//        this.getEntityManager().merge(entity);

    }

//    @Override
//    public List<T> findAll() {
//        return super.findAll(this.isActive());
//    }
//
//    @Override
//    public List<T> findAll(Sort sort) {
//        return super.findAll(this.isActive(), sort);
//    }
//
//    @Override
//    public Optional<T> findById(ID id) {
//        return super.findOne(isActiveById(id));
//    }
//
//    protected JpaEntityInformation<T, ?> getEntityInformation() {
//        return this.entityInformation;
//    }
//
//    public EntityManager getEntityManager() {
//        return this.entityManager;
//    }
//
//    private Specification<T> isActive() {
//        return (Specification<T>) (root, query, criteriaBuilder) -> criteriaBuilder.isTrue(root.get("isActive"));
//    }
//
//    private Specification<T> isActiveById(final ID id) {
//        return (Specification<T>) (root, query, criteriaBuilder) -> {
//            final Predicate idPredicate = criteriaBuilder.equal(root.get("id"), id);
//            final Predicate activePredicate = criteriaBuilder.isTrue(root.get("isActive"));
//            return criteriaBuilder.and(idPredicate, activePredicate);
//        };
//    }
}
