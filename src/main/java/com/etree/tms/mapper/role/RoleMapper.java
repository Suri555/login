package com.etree.tms.mapper.role;

import org.springframework.stereotype.Component;

import com.etree.tms.domain.role.Role;
import com.etree.tms.mapper1.AbstractModelMapper;
import com.etree.tms.model.role.RoleModel;
@Component
public class RoleMapper extends AbstractModelMapper<RoleModel, Role>{

	@Override
	public Class<RoleModel> entityType() {
		
		return RoleModel.class;
	}

	@Override
	public Class<Role> modelType() {
		
		return Role.class;
	}

}
