package com.ssitao.code.modular.iam.userprofile.application.service.impl;

import com.ssitao.code.modular.iam.userprofile.api.dto.IamUserProfileDTO;
import com.ssitao.code.modular.iam.userprofile.application.command.IamUserProfileCreateCommand;
import com.ssitao.code.modular.iam.userprofile.application.command.IamUserProfileQueryCommand;
import com.ssitao.code.modular.iam.userprofile.application.command.IamUserProfileUpdateCommand;
import com.ssitao.code.modular.iam.userprofile.application.service.IamUserProfileAppService;
import com.ssitao.code.modular.iam.userprofile.domain.model.IamUserProfile;
import com.ssitao.code.modular.iam.userprofile.domain.repository.IamUserProfileRepository;
import com.ssitao.code.modular.iam.userprofile.infrastructure.converter.IamUserProfileConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * IAM用户档案应用服务实现
 *
 * @author ssitao-code
 * @since 2.0.0
 */
@Service
public class IamUserProfileAppServiceImpl implements IamUserProfileAppService {

    @Resource
    private IamUserProfileRepository userProfileRepository;

    @Resource
    private IamUserProfileConverter userProfileConverter;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createUserProfile(IamUserProfileCreateCommand command) {
        // 检查用户编码是否已存在
        if (userProfileRepository.existsByCode(command.getUserCode(), command.getSyTenantId(), null)) {
            throw new IllegalArgumentException("用户编码已存在: " + command.getUserCode());
        }

        // 检查手机号是否已存在
        if (command.getUserPhone() != null &&
            userProfileRepository.existsByPhone(command.getUserPhone(), command.getSyTenantId(), null)) {
            throw new IllegalArgumentException("手机号已存在: " + command.getUserPhone());
        }

        // 检查邮箱是否已存在
        if (command.getUserMail() != null &&
            userProfileRepository.existsByEmail(command.getUserMail(), command.getSyTenantId(), null)) {
            throw new IllegalArgumentException("邮箱已存在: " + command.getUserMail());
        }

        IamUserProfile userProfile = new IamUserProfile();
        userProfile.setUserId(UUID.randomUUID().toString().replace("-", ""));
        userProfile.setUserCode(command.getUserCode());
        userProfile.setUserName(command.getUserName());
        userProfile.setUserSexCode(command.getUserSexCode());
        userProfile.setUserSexName(command.getUserSexName());
        userProfile.setUserPhone(command.getUserPhone());
        userProfile.setUserMail(command.getUserMail());
        userProfile.setUserAge(command.getUserAge());
        userProfile.setUserBirth(command.getUserBirth());
        userProfile.setUserEntryDate(command.getUserEntryDate());
        userProfile.setUserAvatar(command.getUserAvatar());
        userProfile.setUserCardnum(command.getUserCardnum());
        userProfile.setUserIdtypeCode(command.getUserIdtypeCode());
        userProfile.setUserIdtypeName(command.getUserIdtypeName());
        userProfile.setUserNativePlace(command.getUserNativePlace());
        userProfile.setUserEducationCode(command.getUserEducationCode());
        userProfile.setUserEducationName(command.getUserEducationName());
        userProfile.setUserPostCode(command.getUserPostCode());
        userProfile.setUserPostName(command.getUserPostName());
        userProfile.setUserMonitordeptId(command.getUserMonitordeptId());
        userProfile.setUserMonitordeptName(command.getUserMonitordeptName());
        userProfile.setUserRoleId(command.getUserRoleId());
        userProfile.setUserRoleName(command.getUserRoleName());
        userProfile.setUserRemark(command.getUserRemark());
        userProfile.setUserAddress(command.getUserAddress());
        userProfile.setUserStation(command.getUserStation());
        userProfile.setUserJobnum(command.getUserJobnum());
        userProfile.setUserNowaddress(command.getUserNowaddress());
        userProfile.setUserPoliticalCode(command.getUserPoliticalCode());
        userProfile.setUserPoliticalName(command.getUserPoliticalName());
        userProfile.setUserContactorName(command.getUserContactorName());
        userProfile.setUserContactorPhone(command.getUserContactorPhone());
        userProfile.setUserTelephone(command.getUserTelephone());
        userProfile.setUserNationCode(command.getUserNationCode());
        userProfile.setUserNationName(command.getUserNationName());
        userProfile.setUserNationalityCode(command.getUserNationalityCode());
        userProfile.setUserNationalityName(command.getUserNationalityName());
        userProfile.setUserEmployeeStatus(command.getUserEmployeeStatus());
        userProfile.setSyOrgId(command.getSyOrgId());
        userProfile.setSyTenantId(command.getSyTenantId());
        userProfile.setSyTenantName(command.getSyTenantName());
        userProfile.setSyStatus("1");

        return userProfileRepository.save(userProfile);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<String> batchCreateUserProfiles(List<IamUserProfileCreateCommand> commands) {
        List<String> ids = new ArrayList<>();
        for (IamUserProfileCreateCommand command : commands) {
            String id = createUserProfile(command);
            ids.add(id);
        }
        return ids;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserProfile(IamUserProfileUpdateCommand command) {
        IamUserProfile userProfile = userProfileRepository.findById(command.getUserId(), null)
                .orElseThrow(() -> new IllegalArgumentException("用户档案不存在: " + command.getUserId()));

        // 更新字段
        if (command.getUserName() != null) {
            userProfile.setUserName(command.getUserName());
        }
        if (command.getUserSexCode() != null) {
            userProfile.setUserSexCode(command.getUserSexCode());
        }
        if (command.getUserSexName() != null) {
            userProfile.setUserSexName(command.getUserSexName());
        }
        if (command.getUserPhone() != null) {
            userProfile.setUserPhone(command.getUserPhone());
        }
        if (command.getUserMail() != null) {
            userProfile.setUserMail(command.getUserMail());
        }
        if (command.getUserAge() != null) {
            userProfile.setUserAge(command.getUserAge());
        }
        if (command.getUserBirth() != null) {
            userProfile.setUserBirth(command.getUserBirth());
        }
        if (command.getUserEntryDate() != null) {
            userProfile.setUserEntryDate(command.getUserEntryDate());
        }
        if (command.getUserLeaveDate() != null) {
            userProfile.setUserLeaveDate(command.getUserLeaveDate());
        }
        if (command.getUserAvatar() != null) {
            userProfile.setUserAvatar(command.getUserAvatar());
        }
        if (command.getUserCardnum() != null) {
            userProfile.setUserCardnum(command.getUserCardnum());
        }
        if (command.getUserIdtypeCode() != null) {
            userProfile.setUserIdtypeCode(command.getUserIdtypeCode());
        }
        if (command.getUserIdtypeName() != null) {
            userProfile.setUserIdtypeName(command.getUserIdtypeName());
        }
        if (command.getUserNativePlace() != null) {
            userProfile.setUserNativePlace(command.getUserNativePlace());
        }
        if (command.getUserEducationCode() != null) {
            userProfile.setUserEducationCode(command.getUserEducationCode());
        }
        if (command.getUserEducationName() != null) {
            userProfile.setUserEducationName(command.getUserEducationName());
        }
        if (command.getUserPostCode() != null) {
            userProfile.setUserPostCode(command.getUserPostCode());
        }
        if (command.getUserPostName() != null) {
            userProfile.setUserPostName(command.getUserPostName());
        }
        if (command.getUserMonitordeptId() != null) {
            userProfile.setUserMonitordeptId(command.getUserMonitordeptId());
        }
        if (command.getUserMonitordeptName() != null) {
            userProfile.setUserMonitordeptName(command.getUserMonitordeptName());
        }
        if (command.getUserRoleId() != null) {
            userProfile.setUserRoleId(command.getUserRoleId());
        }
        if (command.getUserRoleName() != null) {
            userProfile.setUserRoleName(command.getUserRoleName());
        }
        if (command.getUserRemark() != null) {
            userProfile.setUserRemark(command.getUserRemark());
        }
        if (command.getUserAddress() != null) {
            userProfile.setUserAddress(command.getUserAddress());
        }
        if (command.getUserStation() != null) {
            userProfile.setUserStation(command.getUserStation());
        }
        if (command.getUserJobnum() != null) {
            userProfile.setUserJobnum(command.getUserJobnum());
        }
        if (command.getUserNowaddress() != null) {
            userProfile.setUserNowaddress(command.getUserNowaddress());
        }
        if (command.getUserPoliticalCode() != null) {
            userProfile.setUserPoliticalCode(command.getUserPoliticalCode());
        }
        if (command.getUserPoliticalName() != null) {
            userProfile.setUserPoliticalName(command.getUserPoliticalName());
        }
        if (command.getUserContactorName() != null) {
            userProfile.setUserContactorName(command.getUserContactorName());
        }
        if (command.getUserContactorPhone() != null) {
            userProfile.setUserContactorPhone(command.getUserContactorPhone());
        }
        if (command.getUserTelephone() != null) {
            userProfile.setUserTelephone(command.getUserTelephone());
        }
        if (command.getUserNationCode() != null) {
            userProfile.setUserNationCode(command.getUserNationCode());
        }
        if (command.getUserNationName() != null) {
            userProfile.setUserNationName(command.getUserNationName());
        }
        if (command.getUserNationalityCode() != null) {
            userProfile.setUserNationalityCode(command.getUserNationalityCode());
        }
        if (command.getUserNationalityName() != null) {
            userProfile.setUserNationalityName(command.getUserNationalityName());
        }
        if (command.getUserEmployeeStatus() != null) {
            userProfile.setUserEmployeeStatus(command.getUserEmployeeStatus());
        }
        if (command.getSyStatus() != null) {
            userProfile.setSyStatus(command.getSyStatus());
        }
        if (command.getSyOrgId() != null) {
            userProfile.setSyOrgId(command.getSyOrgId());
        }

        userProfileRepository.update(userProfile);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUserProfile(String id, String tenantId) {
        userProfileRepository.deleteById(id, tenantId);
    }

    @Override
    public IamUserProfileDTO getUserProfileById(String id, String tenantId) {
        IamUserProfile userProfile = userProfileRepository.findById(id, tenantId)
                .orElse(null);
        return userProfileConverter.toDTO(userProfile);
    }

    @Override
    public IamUserProfileDTO getUserProfileByCode(String userCode, String tenantId) {
        IamUserProfile userProfile = userProfileRepository.findByCode(userCode, tenantId)
                .orElse(null);
        return userProfileConverter.toDTO(userProfile);
    }

    @Override
    public List<IamUserProfileDTO> listUserProfiles(IamUserProfileQueryCommand command) {
        List<IamUserProfile> userProfiles = userProfileRepository.findAll(command.getSyTenantId());
        return userProfileConverter.toDTOList(userProfiles);
    }

    @Override
    public List<IamUserProfileDTO> pageUserProfiles(IamUserProfileQueryCommand command, int page, int size) {
        List<IamUserProfile> userProfiles = userProfileRepository.findPage(
                command.getSyTenantId(),
                command.getKeyword(),
                command.getUserMonitordeptId(),
                page,
                size
        );
        return userProfileConverter.toDTOList(userProfiles);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enableUserProfile(String id, String tenantId) {
        IamUserProfile userProfile = userProfileRepository.findById(id, tenantId)
                .orElseThrow(() -> new IllegalArgumentException("用户档案不存在: " + id));
        userProfile.enable();
        userProfileRepository.update(userProfile);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disableUserProfile(String id, String tenantId) {
        IamUserProfile userProfile = userProfileRepository.findById(id, tenantId)
                .orElseThrow(() -> new IllegalArgumentException("用户档案不存在: " + id));
        userProfile.disable();
        userProfileRepository.update(userProfile);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignDepartment(String id, String deptId, String deptName, String tenantId) {
        IamUserProfile userProfile = userProfileRepository.findById(id, tenantId)
                .orElseThrow(() -> new IllegalArgumentException("用户档案不存在: " + id));
        userProfile.assignDepartment(deptId, deptName);
        userProfileRepository.update(userProfile);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignPost(String id, String postCode, String postName, String tenantId) {
        IamUserProfile userProfile = userProfileRepository.findById(id, tenantId)
                .orElseThrow(() -> new IllegalArgumentException("用户档案不存在: " + id));
        userProfile.assignPost(postCode, postName);
        userProfileRepository.update(userProfile);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void assignRole(String id, String roleId, String roleName, String tenantId) {
        IamUserProfile userProfile = userProfileRepository.findById(id, tenantId)
                .orElseThrow(() -> new IllegalArgumentException("用户档案不存在: " + id));
        userProfile.assignRole(roleId, roleName);
        userProfileRepository.update(userProfile);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBasicInfo(String id, IamUserProfileUpdateCommand command) {
        IamUserProfile userProfile = userProfileRepository.findById(id, null)
                .orElseThrow(() -> new IllegalArgumentException("用户档案不存在: " + id));
        if (command.getUserName() != null) {
            userProfile.setUserName(command.getUserName());
        }
        if (command.getUserSexCode() != null) {
            userProfile.setUserSexCode(command.getUserSexCode());
        }
        if (command.getUserSexName() != null) {
            userProfile.setUserSexName(command.getUserSexName());
        }
        if (command.getUserAge() != null) {
            userProfile.setUserAge(command.getUserAge());
        }
        if (command.getUserBirth() != null) {
            userProfile.setUserBirth(command.getUserBirth());
        }
        if (command.getUserCardnum() != null) {
            userProfile.setUserCardnum(command.getUserCardnum());
        }
        userProfileRepository.update(userProfile);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateContactInfo(String id, IamUserProfileUpdateCommand command) {
        IamUserProfile userProfile = userProfileRepository.findById(id, null)
                .orElseThrow(() -> new IllegalArgumentException("用户档案不存在: " + id));
        if (command.getUserPhone() != null) {
            userProfile.setUserPhone(command.getUserPhone());
        }
        if (command.getUserMail() != null) {
            userProfile.setUserMail(command.getUserMail());
        }
        if (command.getUserAddress() != null) {
            userProfile.setUserAddress(command.getUserAddress());
        }
        if (command.getUserNowaddress() != null) {
            userProfile.setUserNowaddress(command.getUserNowaddress());
        }
        if (command.getUserContactorName() != null) {
            userProfile.setUserContactorName(command.getUserContactorName());
        }
        if (command.getUserContactorPhone() != null) {
            userProfile.setUserContactorPhone(command.getUserContactorPhone());
        }
        if (command.getUserTelephone() != null) {
            userProfile.setUserTelephone(command.getUserTelephone());
        }
        userProfileRepository.update(userProfile);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateJobInfo(String id, IamUserProfileUpdateCommand command) {
        IamUserProfile userProfile = userProfileRepository.findById(id, null)
                .orElseThrow(() -> new IllegalArgumentException("用户档案不存在: " + id));
        if (command.getUserPostCode() != null) {
            userProfile.setUserPostCode(command.getUserPostCode());
        }
        if (command.getUserPostName() != null) {
            userProfile.setUserPostName(command.getUserPostName());
        }
        if (command.getUserMonitordeptId() != null) {
            userProfile.setUserMonitordeptId(command.getUserMonitordeptId());
        }
        if (command.getUserMonitordeptName() != null) {
            userProfile.setUserMonitordeptName(command.getUserMonitordeptName());
        }
        if (command.getUserRoleId() != null) {
            userProfile.setUserRoleId(command.getUserRoleId());
        }
        if (command.getUserRoleName() != null) {
            userProfile.setUserRoleName(command.getUserRoleName());
        }
        if (command.getUserEntryDate() != null) {
            userProfile.setUserEntryDate(command.getUserEntryDate());
        }
        if (command.getUserEmployeeStatus() != null) {
            userProfile.setUserEmployeeStatus(command.getUserEmployeeStatus());
        }
        userProfileRepository.update(userProfile);
    }

    @Override
    public List<IamUserProfileDTO> getUserProfilesByDepartment(String deptId, String tenantId) {
        List<IamUserProfile> userProfiles = userProfileRepository.findByDepartmentId(deptId, tenantId);
        return userProfileConverter.toDTOList(userProfiles);
    }

    @Override
    public List<IamUserProfileDTO> getUserProfilesByPost(String postCode, String tenantId) {
        List<IamUserProfile> userProfiles = userProfileRepository.findByPostCode(postCode, tenantId);
        return userProfileConverter.toDTOList(userProfiles);
    }

    @Override
    public List<IamUserProfileDTO> searchUserProfiles(String keyword, String tenantId) {
        List<IamUserProfile> userProfiles = userProfileRepository.findPage(tenantId, keyword, null, 1, 100);
        return userProfileConverter.toDTOList(userProfiles);
    }

}
