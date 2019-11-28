package com.cartisan.repositories;

import com.cartisan.domains.AggregateRoot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * <p>Title: BaseRepository</p>
 * <p>
 *     Repository 基类，已经包含了常用的增删改查操作。<br/>
 *     使用时只需要继承接口，不需要实现类，spring 自动通过 cglib 生成实现类
 * </p>
 *
 * @author colin
 */
//@NoRepositoryBean
//public interface BaseRepository<T extends AbstractEntity , ID extends Serializable>
//        extends /*CrudRepository<T, ID>*/JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
//}
@NoRepositoryBean
public interface BaseRepository<T extends AggregateRoot, ID extends Serializable>
        extends /*CrudRepository<T, ID>*/JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
}
