#1.选中要分配角色的用户数据（先选中一行,并将当前用户的ID传入到控制器）


#2.弹出分配角色的窗口，将所有角色列表显示出来，并选中当前用户已经拥有的角色


#3.选中角色后，点击确认时：

	#3.1：先删除sys_role_user（角色用户关系表）的数据
	
	DELETE FROM sys_role_user WHERE uid = #{userId}
	
	#3.2:再往sys_role_user插入新的关系数据
	
	INSERT INTO sys_role_user (rid,uid) VALUES(#{rid},#{uid})
	
