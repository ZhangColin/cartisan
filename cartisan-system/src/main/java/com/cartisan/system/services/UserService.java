package com.cartisan.system.services;

import com.cartisan.common.dtos.PageResult;
import com.cartisan.common.exceptions.CartisanException;
import com.cartisan.common.utils.IdWorker;
import com.cartisan.system.domains.User;
import com.cartisan.system.dtos.UserDto;
import com.cartisan.system.params.UserParam;
import com.cartisan.system.params.UserSearchParam;
import com.cartisan.system.repositories.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author colin
 */
@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private IdWorker idWorker;

    public UserDto getUser(Long id) {
        return UserDto.convertFrom(repository.findById(id).get());
    }

    public Optional<User> findByUserName(String username) {
        return repository.findByUsername(username);
    }

    public PageResult<UserDto> searchUsers(UserSearchParam searchParam, Integer currentPage, Integer pageSize) {
        PageRequest pageRequest = PageRequest.of(currentPage - 1, pageSize);

        final Page<User> searchResult = repository.findAll(new UserSearchSpecification(searchParam), pageRequest);

        return new PageResult<>(searchResult.getTotalElements(), searchResult.getTotalPages(),
                searchResult.map(UserDto::convertFrom).getContent());
    }

    @Transactional(rollbackOn = Exception.class)
    public void addUser(UserParam userParam) {
        if (repository.existsByEmail(userParam.getEmail())) {
            throw new CartisanException("邮箱已被占用");
        }

        if (repository.existsByPhone(userParam.getPhone())) {
            throw new CartisanException("电话已被占用");
        }

        final User user = new User(idWorker.nextId(), userParam.getUsername(), userParam.getPassword());
        fillUserInfo(userParam, user);

        user.assignRoles(userParam.getRoleCodes());
        user.assignDepartments(userParam.getDepartmentIds());


        // TODO: operator info
        user.setOperator("system");
        user.setOperateIp("127.0.0.1");

        repository.save(user);
    }

    @Transactional(rollbackOn = Exception.class)
    public void editUser(Long id, UserParam userParam) {
        if (repository.existsByEmailAndIdNot(userParam.getEmail(), id)) {
            throw new CartisanException("邮箱已被占用");
        }

        if (repository.existsByPhoneAndIdNot(userParam.getPhone(), id)) {
            throw new CartisanException("电话已被占用");
        }

        final Optional<User> userOptional = repository.findById(id);

        if (!userOptional.isPresent()) {
            throw new CartisanException("待更新的用户不存在");
        }

        final User user = userOptional.get();

        fillUserInfo(userParam, user);

        user.assignRoles(userParam.getRoleCodes());
        user.assignDepartments(userParam.getDepartmentIds());

        // TODO: operator info
        user.setOperator("system update");
        user.setOperateIp("127.0.0.1");

        repository.save(user);
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeUser(long id) {
        repository.deleteById(id);
    }

    @Transactional(rollbackOn = Exception.class)
    public void frozen(Long id) {
        final Optional<User> userOptional = repository.findById(id);

        if (!userOptional.isPresent()) {
            throw new CartisanException("待更新的用户不存在");
        }

        final User user = userOptional.get();

        user.frozen();

        // TODO: operator info
        user.setOperator("system update");
        user.setOperateIp("127.0.0.1");

        repository.save(user);
    }

    @Transactional(rollbackOn = Exception.class)
    public void unFrozen(Long id) {
        final Optional<User> userOptional = repository.findById(id);

        if (!userOptional.isPresent()) {
            throw new CartisanException("待更新的用户不存在");
        }

        final User user = userOptional.get();

        user.unFrozen();

        // TODO: operator info
        user.setOperator("system update");
        user.setOperateIp("127.0.0.1");

        repository.save(user);
    }

    @Transactional(rollbackOn = Exception.class)
    public void changePassword(Long id, String password) {
        final Optional<User> userOptional = repository.findById(id);

        if (!userOptional.isPresent()) {
            throw new CartisanException("待更新的用户不存在");
        }

        final User user = userOptional.get();

        user.changePassword(password);

        // TODO: operator info
        user.setOperator("system update");
        user.setOperateIp("127.0.0.1");

        repository.save(user);
    }


    private void fillUserInfo(UserParam userParam, User user) {
        user.setRealName(userParam.getRealName());
        user.setAvatar(userParam.getAvatar());
        user.setBirthday(userParam.getBirthday());
        user.setSex(userParam.getSex());
        user.setEmail(userParam.getEmail());
        user.setPhone(userParam.getPhone());
    }

    private class UserSearchSpecification implements Specification<User> {

        private final UserSearchParam searchParam;

        public UserSearchSpecification(UserSearchParam searchParam) {
            this.searchParam = searchParam;
        }

        @Override
        public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicateList = new ArrayList<>();

            if (StringUtils.isNotBlank(searchParam.getUsername())) {
                predicateList.add(criteriaBuilder.like(root.get("username"),
                        "%" + searchParam.getUsername() + "%"));
            }

            if (StringUtils.isNotBlank(searchParam.getEmail())) {
                predicateList.add(criteriaBuilder.like(root.get("email"),
                        "%" + searchParam.getEmail() + "%"));
            }

            if (StringUtils.isNotBlank(searchParam.getPhone())) {
                predicateList.add(criteriaBuilder.like(root.get("phone"),
                        "%" + searchParam.getPhone() + "%"));
            }

            if (searchParam.getSex() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("sex"), searchParam.getSex()));
            }

            if (searchParam.getStatus() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("status"), searchParam.getStatus()));
            }

            return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }
    }
}
