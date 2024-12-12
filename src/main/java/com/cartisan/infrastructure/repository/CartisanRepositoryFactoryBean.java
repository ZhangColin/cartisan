package com.cartisan.infrastructure.repository;

import com.cartisan.infrastructure.domain.AggregateRoot;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import java.io.Serializable;

/**
 * @author zhangcolin
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
            protected JpaRepositoryImplementation<?, ?> getTargetRepository(RepositoryInformation information, EntityManager entityManager) {
                return new BaseRepositoryImpl<>((Class<T>) information.getDomainType(), entityManager);
            }

            @Override
            protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
                return BaseRepositoryImpl.class;
            }
        };
    }
}
