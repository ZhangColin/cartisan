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

    public CategoryDto getCategory(Long categoryId) {
        return CategoryDto.convertFrom(findCategoryById(categoryId));
    }


    @Transactional(rollbackOn = Exception.class)
    public CategoryDto addCategory(CategoryParam categoryParam) {
        if (repository.existsByName(categoryParam.getName())) {
            throw new CartisanException(CouponCodeMessage.SAME_CATEGORY_NAME);
        }

        final Category category = new Category(idWorker.nextId(), categoryParam.getName(),
                categoryParam.getIcon());

        category.setSort(categoryParam.getSort());

        repository.save(category);

        return CategoryDto.convertFrom(category);
    }

    @Transactional(rollbackOn = Exception.class)
    public CategoryDto editCategory(Long id, CategoryParam categoryParam) {
        if (repository.existsByNameAndIdNot(categoryParam.getName(), id)) {
            throw new CartisanException(CouponCodeMessage.SAME_CATEGORY_NAME);
        }

        final Category category = findCategoryById(id);
        category.changeInfo(categoryParam.getName(), categoryParam.getIcon());
        category.setSort(categoryParam.getSort());

        repository.save(category);

        return CategoryDto.convertFrom(category);
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeCategory(long id) {
        repository.delete(findCategoryById(id));
    }

    private Category findCategoryById(Long categoryId) {
        final Optional<Category> categoryOptional = repository.findById(categoryId);
        if (!categoryOptional.isPresent()) {
            throw new CartisanException(CouponCodeMessage.CATEGORY_NOT_EXIST);
        }
        return categoryOptional.get();
    }
}
