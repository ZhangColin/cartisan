package com.cartisan.common.repositories;

import com.cartisan.common.domains.AggregateRoot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * @author colin
 */
public class CartisanRepositoryFactoryBean<R extends JpaRepository<T, Serializable>, T extends AggregateRoot>
        extends JpaRepositoryFactoryBean<R, T, Serializable> {
    public CartisanRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new JpaRepositoryFactory(entityManager){
            @Override
            protected Object getTargetRepository(RepositoryInformation information) {
                return new BaseRepositoryImpl<>((Class<T>) information.getDomainType(), entityManager);
            }
            //            @Override
//            protected Object getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
//                return new BaseRepositoryImpl<>((Class<T>) information.getDomainType(), entityManager);
////                return new BaseRepositoryImpl<>(this.getEntityInformation((Class<T>) information.getDomainType()), entityManager);
//            }

            @Override
            protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
                return BaseRepositoryImpl.class;
            }
        };
    }
}

/**

 可适配不需要继承 AbstractEntity 的实体对象，仓储接口直接继承 JpaRepository, 使用 SimpleJpaRepository 的实现
 基于统一框架约束的考虑，不支持这样的适配

 public class CartisanRepositoryFactoryBean<R extends JpaRepository<T, Serializable>, T>
        extends JpaRepositoryFactoryBean<R, T, Serializable> {
    public CartisanRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new JpaRepositoryFactory(entityManager) {
            @Override
            protected JpaRepositoryImplementation<?, ?> getTargetRepository(
                    RepositoryInformation information, EntityManager entityManager) {
                final Class<?> domainClass = information.getDomainType();
                if (AbstractEntity.class.isAssignableFrom(domainClass)) {
                    return new BaseRepositoryImpl(domainClass, entityManager);
                } else {
                    return new SimpleJpaRepository(domainClass, entityManager);
                }
            }

            @Override
            protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
                return metadata.getDomainType().isAssignableFrom(AbstractEntity.class) ?
                        BaseRepositoryImpl.class : SimpleJpaRepository.class;
            }
        };
    }
}

 */
