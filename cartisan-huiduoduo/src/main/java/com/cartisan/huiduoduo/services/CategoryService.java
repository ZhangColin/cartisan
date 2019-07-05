package com.cartisan.huiduoduo.services;

import com.cartisan.common.exceptions.CartisanException;
import com.cartisan.common.utils.IdWorker;
import com.cartisan.huiduoduo.constants.CouponCodeMessage;
import com.cartisan.huiduoduo.domains.Category;
import com.cartisan.huiduoduo.dtos.CategoryDto;
import com.cartisan.huiduoduo.params.CategoryParam;
import com.cartisan.huiduoduo.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * @author colin
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    @Autowired
    private IdWorker idWorker;

    public List<CategoryDto> getCategories() {
        final List<Category> categories = repository.findAll(new Sort(Sort.Direction.ASC, "sort"));

        return categories.stream().map(CategoryDto::convertFrom).collect(toList());
    }


    @Transactional(rollbackOn = Exception.class)
    public void addCategory(CategoryParam categoryParam) {
        if (repository.existsByName(categoryParam.getName())) {
            throw new CartisanException(CouponCodeMessage.SAME_CATEGORY_NAME);
        }

        final Category category = new Category(idWorker.nextId(), categoryParam.getName(),
                categoryParam.getIcon());

        category.setSort(categoryParam.getSort());

        repository.save(category);
    }

    @Transactional(rollbackOn = Exception.class)
    public void editCategory(Long id, CategoryParam categoryParam) {
        if (repository.existsByNameAndIdNot(categoryParam.getName(), id)) {
            throw new CartisanException(CouponCodeMessage.SAME_CATEGORY_NAME);
        }

        final Optional<Category> categoryOptional = repository.findById(id);
        if (!categoryOptional.isPresent()) {
            throw new CartisanException(CouponCodeMessage.CATEGORY_NOT_EXIST);
        }

        final Category category = categoryOptional.get();
        category.changeInfo(categoryParam.getName(), categoryParam.getIcon());
        category.setSort(categoryParam.getSort());

        repository.save(category);
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeCategory(long id) {
        final Optional<Category> categoryOptional = repository.findById(id);
        if (!categoryOptional.isPresent()) {
            throw new CartisanException(CouponCodeMessage.CATEGORY_NOT_EXIST);
        }

        repository.deleteById(id);
    }
}
