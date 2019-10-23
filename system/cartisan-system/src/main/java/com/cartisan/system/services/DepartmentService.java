package com.cartisan.system.services;

import com.cartisan.common.exceptions.CartisanException;
import com.cartisan.common.utils.SnowflakeIdWorker;
import com.cartisan.system.constants.SystemCodeMessage;
import com.cartisan.system.domains.Department;
import com.cartisan.system.dtos.DepartmentDto;
import com.cartisan.system.params.DepartmentParam;
import com.cartisan.system.queries.UserQueryMapper;
import com.cartisan.system.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author colin
 */
@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository repository;

    @Autowired
    private UserQueryMapper userQueryMapper;

    @Autowired
    private SnowflakeIdWorker idWorker;

    public List<DepartmentDto> getDepartmentList() {
        final List<Department> departments = repository.findAll(new Sort(Sort.Direction.ASC, "sort"));

        return DepartmentDto.buildDepartmentTreeList(departments);
    }


    @Transactional(rollbackOn = Exception.class)
    public void addDepartment(DepartmentParam departmentParam) {
        if (repository.existsByParentIdAndName(departmentParam.getParentId(), departmentParam.getName())) {
            throw new CartisanException(SystemCodeMessage.SAME_DEPARTMENT_NAME);
        }

        final Department department = new Department(
                idWorker.nextId(),
                departmentParam.getParentId(),
                departmentParam.getName());

        department.setDescription(departmentParam.getDescription());
        department.setSort(departmentParam.getSort());


        repository.save(department);
    }

    @Transactional(rollbackOn = Exception.class)
    public void editDepartment(Long id, DepartmentParam departmentParam) {
        if (repository.existsByParentIdAndNameAndIdNot(departmentParam.getParentId(), departmentParam.getName(), id)) {
            throw new CartisanException(SystemCodeMessage.SAME_DEPARTMENT_NAME);
        }

        final Optional<Department> departmentOptional = repository.findById(id);
        if (!departmentOptional.isPresent()) {
            throw new CartisanException(SystemCodeMessage.DEPARTMENT_NOT_EXIST);
        }

        final Department department = departmentOptional.get();
        department.setParentId(departmentParam.getParentId());
        department.setName(departmentParam.getName());

        department.setDescription(departmentParam.getDescription());
        department.setSort(departmentParam.getSort());

        repository.save(department);
    }


    @Transactional(rollbackOn = Exception.class)
    public void removeDepartment(long id) {
        final Optional<Department> departmentOptional = repository.findById(id);
        if (!departmentOptional.isPresent()) {
            throw new CartisanException(SystemCodeMessage.DEPARTMENT_NOT_EXIST);
        }

        if (repository.existsByParentId(id)) {
            throw new CartisanException(SystemCodeMessage.HAS_CHILD_DEPARTMENT);
        }

        if (userQueryMapper.existsUserInDepartment(id)) {
            throw new CartisanException(SystemCodeMessage.HAS_USER_IN_DEPARTMENT);
        }

        repository.deleteById(id);
    }
}
