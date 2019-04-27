package com.cartisan.system.services;

import com.cartisan.common.exceptions.CartisanException;
import com.cartisan.common.utils.IdWorker;
import com.cartisan.system.domains.Department;
import com.cartisan.system.dtos.DepartmentDto;
import com.cartisan.system.params.DepartmentParam;
import com.cartisan.system.queries.UserQuery;
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
    private UserQuery userQuery;

    @Autowired
    private IdWorker idWorker;

    public List<DepartmentDto> getDepartmentList() {
        final List<Department> departments = repository.findAll(new Sort(Sort.Direction.ASC, "sort"));

        return DepartmentDto.buildDepartmentTreeList(departments);
    }


    @Transactional(rollbackOn = Exception.class)
    public void addDepartment(DepartmentParam departmentParam) {
        if (repository.existsByParentIdAndName(departmentParam.getParentId(), departmentParam.getName())) {
            throw new CartisanException("同一层级下存在相同名称的部门");
        }

        final Department department = new Department(
                idWorker.nextId(),
                departmentParam.getParentId(),
                departmentParam.getName());

        department.setDescription(departmentParam.getDescription());
        department.setSort(departmentParam.getSort());

        department.setOperator("system");
        department.setOperateIp("127.0.0.1");

        repository.save(department);
    }

    @Transactional(rollbackOn = Exception.class)
    public void editDepartment(Long id, DepartmentParam departmentParam) {
        if (repository.existsByParentIdAndNameAndIdNot(departmentParam.getParentId(), departmentParam.getName(), id)) {
            throw new CartisanException("同一层级下存在相同名称的部门");
        }

        final Optional<Department> departmentOptional = repository.findById(id);
        if (!departmentOptional.isPresent()) {
            throw new CartisanException("待更新的部门不存在");
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
            throw new CartisanException("待删除的部门不存在");
        }

        if (repository.existsByParentId(id)) {
            throw new CartisanException("当前部门下面有子部门，无法删除");
        }

        if (userQuery.existsUserInDepartment(id)) {
            throw new CartisanException("当前部门下面有用户，无法删除");
        }

        repository.deleteById(id);
    }
}
