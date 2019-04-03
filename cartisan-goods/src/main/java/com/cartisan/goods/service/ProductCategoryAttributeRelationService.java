package com.cartisan.goods.service;

import com.cartisan.goods.domain.ProductCategoryAttributeRelation;
import com.cartisan.goods.repository.ProductCategoryAttributeRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author colin
 */
@Service
public class ProductCategoryAttributeRelationService {
    @Autowired
    private ProductCategoryAttributeRelationRepository repository;


    @Transactional(rollbackOn = Exception.class)
    public void addRelations(Long categoryId, List<Long> attributeIds) {
        attributeIds.stream().forEach(attributeId -> repository.save(
                new ProductCategoryAttributeRelation(categoryId, attributeId)));
    }

    @Transactional(rollbackOn = Exception.class)
    public void editRelations(Long categoryId, List<Long> attributeIds) {
        final List<ProductCategoryAttributeRelation> relations = getProductCategoryAttributeRelations(categoryId);

        final List<Long> existAttributeIds = relations.stream().map(relation -> relation.getAttributeId()).collect(Collectors.toList());

        attributeIds.stream().filter(attributeId -> !existAttributeIds.contains(attributeId))
                .forEach(attributeId -> repository.save(new ProductCategoryAttributeRelation(categoryId, attributeId)));

        relations.stream().filter(relation -> !attributeIds.contains(relation.getAttributeId()))
                .forEach(relation -> repository.delete(relation));
    }

    private List<ProductCategoryAttributeRelation> getProductCategoryAttributeRelations(Long categoryId) {
        return repository.findAll((Specification<ProductCategoryAttributeRelation>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            predicateList.add(criteriaBuilder.equal(root.get("categoryId"), categoryId));
//                    final CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get("attributeId"));
//                    attributeIds.stream().forEach(attributeId -> in.value(attributeId));
//                    predicateList.add(in);

            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        });
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeRelations(Long categoryId) {
        getProductCategoryAttributeRelations(categoryId).forEach(relation -> repository.delete(relation));
    }

}
