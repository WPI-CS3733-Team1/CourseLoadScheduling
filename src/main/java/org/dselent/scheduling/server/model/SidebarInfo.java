package org.dselent.scheduling.server.model;

import java.sql.JDBCType;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dselent.scheduling.server.model.User.Columns;

public class SidebarInfo {
	
	// table name
		public static final String TABLE_NAME = "sidebar_info";
			
		// column names
		public static enum Columns
		{
			//
			ID,
			USER_NAME,
			FIRST_NAME,
			LAST_NAME,
			EMAIL,	
			USER_ROLE,
			DEPARTMENT,
			CREATED_AT,
			UPDATED_AT
		}
		
		// enum list
		private static final List<Columns> COLUMN_LIST = new ArrayList<>();
		
		// type mapping
		private static final Map<Columns, JDBCType> COLUMN_TYPE_MAP = new HashMap<>();
		
		static
		{
			for(Columns key : Columns.values())
			{
				COLUMN_LIST.add(key);
			}
			
			COLUMN_TYPE_MAP.put(Columns.ID, JDBCType.INTEGER);
			COLUMN_TYPE_MAP.put(Columns.USER_NAME, JDBCType.VARCHAR);
			COLUMN_TYPE_MAP.put(Columns.FIRST_NAME, JDBCType.VARCHAR);
			COLUMN_TYPE_MAP.put(Columns.LAST_NAME, JDBCType.VARCHAR);
			COLUMN_TYPE_MAP.put(Columns.USER_ROLE, JDBCType.VARCHAR);
			COLUMN_TYPE_MAP.put(Columns.DEPARTMENT, JDBCType.VARCHAR);
			COLUMN_TYPE_MAP.put(Columns.CREATED_AT, JDBCType.TIMESTAMP_WITH_TIMEZONE);
			COLUMN_TYPE_MAP.put(Columns.UPDATED_AT, JDBCType.TIMESTAMP_WITH_TIMEZONE);
		};
		
		// attributes
		
		private Integer id;
		private String userName;
		private String firstName;
		private String lastName;
		private String email;
		private String userRole;
		private String department;
		private Instant createdAt;
		private Instant updatedAt;

		// methods
			
		public static JDBCType getColumnType(Columns column)
		{
			return COLUMN_TYPE_MAP.get(column);
		}
		
		public static String getColumnName(Columns column)
		{
			return column.toString().toLowerCase();
		}
		
		public static List<String> getColumnNameList()
		{
			List<String> columnNameList = new ArrayList<>();
			
			for(Columns column : COLUMN_LIST)
			{
				columnNameList.add(getColumnName(column));
			}
			
			return columnNameList;
		}
		
		//
		
		public Integer getId()
		{
			return id;
		}

		public void setId(Integer id)
		{
			this.id = id;
		}

		public String getUserName()
		{
			return userName;
		}

		public void setUserName(String userName)
		{
			this.userName = userName;
		}

		public String getFirstName()
		{
			return firstName;
		}

		public void setFirstName(String firstName)
		{
			this.firstName = firstName;
		}

		public String getLastName()
		{
			return lastName;
		}

		public void setLastName(String lastName)
		{
			this.lastName = lastName;
		}

		public String getEmail()
		{
			return email;
		}

		public void setEmail(String email)
		{
			this.email = email;
		}

		public String getUserRole() {
			return userRole;
		}

		public void setUserRole(String userRole) {
			this.userRole = userRole;
		}
		
		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}
		
		public Instant getCreatedAt()
		{
			return createdAt;
		}

		public void setCreatedAt(Instant createdAt)
		{
			this.createdAt = createdAt;
		}
		
		public void setCreatedAt(Timestamp createdAt)
		{
			if(createdAt != null)
			{
				this.createdAt = createdAt.toInstant();
			}
		}

		public Instant getUpdatedAt()
		{
			return updatedAt;
		}

		public void setUpdatedAt(Instant updatedAt)
		{
			this.updatedAt = updatedAt;
		}
		
		public void setUpdatedAt(Timestamp updatedAt)
		{
			if(updatedAt != null)
			{
				this.updatedAt = updatedAt.toInstant();
			}
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
			result = prime * result + ((email == null) ? 0 : email.hashCode());
			result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
			result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
			result = prime * result + ((department == null) ? 0 : department.hashCode());
			result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
			result = prime * result + ((userName == null) ? 0 : userName.hashCode());
			return result;
		}

		/*
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			User other = (User) obj;
			if (createdAt == null) {
				if (other.createdAt != null)
					return false;
			} else if (!createdAt.equals(other.createdAt))
				return false;
			if (deptId == null) {
				if (other.deptId != null)
					return false;
			} else if (!deptId.equals(other.deptId))
				return false;
			if (email == null) {
				if (other.email != null)
					return false;
			} else if (!email.equals(other.email))
				return false;
			if (encryptedPassword == null) {
				if (other.encryptedPassword != null)
					return false;
			} else if (!encryptedPassword.equals(other.encryptedPassword))
				return false;
			if (firstName == null) {
				if (other.firstName != null)
					return false;
			} else if (!firstName.equals(other.firstName))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (lastName == null) {
				if (other.lastName != null)
					return false;
			} else if (!lastName.equals(other.lastName))
				return false;
			if (salt == null) {
				if (other.salt != null)
					return false;
			} else if (!salt.equals(other.salt))
				return false;
			if (updatedAt == null) {
				if (other.updatedAt != null)
					return false;
			} else if (!updatedAt.equals(other.updatedAt))
				return false;
			if (userName == null) {
				if (other.userName != null)
					return false;
			} else if (!userName.equals(other.userName))
				return false;
			if (userStateId == null) {
				if (other.userStateId != null)
					return false;
			} else if (!userStateId.equals(other.userStateId))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", userName=" + userName + ", firstName=" + firstName + ", lastName=" + lastName
					+ ", email=" + email + ", encryptedPassword=" + encryptedPassword + ", salt=" + salt + ", userStateId="
					+ userStateId + ", deptId=" + deptId + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
		}
		*/
}
