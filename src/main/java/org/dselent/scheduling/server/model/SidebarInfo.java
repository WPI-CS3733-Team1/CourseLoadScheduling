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
			ROLE_NAME,
			DEPARTMENT_NAME,
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
			COLUMN_TYPE_MAP.put(Columns.EMAIL, JDBCType.VARCHAR);
			COLUMN_TYPE_MAP.put(Columns.ROLE_NAME, JDBCType.VARCHAR);
			COLUMN_TYPE_MAP.put(Columns.DEPARTMENT_NAME, JDBCType.VARCHAR);
			COLUMN_TYPE_MAP.put(Columns.CREATED_AT, JDBCType.TIMESTAMP_WITH_TIMEZONE);
			COLUMN_TYPE_MAP.put(Columns.UPDATED_AT, JDBCType.TIMESTAMP_WITH_TIMEZONE);
		};
		
		// attributes
		
		private Integer id;
		private String userName;
		private String firstName;
		private String lastName;
		private String email;
		private String roleName;
		private String departmentName;
		private Instant createdAt;
		private Instant updatedAt;
		private List<String> courses;

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

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getRoleName() {
			return roleName;
		}

		public void setRoleName(String roleName) {
			this.roleName = roleName;
		}

		public String getDepartmentName() {
			return departmentName;
		}

		public void setDepartmentName(String departmentName) {
			this.departmentName = departmentName;
		}

		public Instant getCreatedAt() {
			return createdAt;
		}

		public void setCreatedAt(Timestamp createdAt) {
			this.createdAt = createdAt.toInstant();
		}

		public Instant getUpdatedAt() {
			return updatedAt;
		}

		public void setUpdatedAt(Timestamp updatedAt) {
			this.updatedAt = updatedAt.toInstant();
		}

		public List<String> getCourses() {
			return courses;
		}

		public void setCourses(List<String> courses) {
			this.courses = courses;
		}

		public static String getTableName() {
			return TABLE_NAME;
		}

		public static List<Columns> getColumnList() {
			return COLUMN_LIST;
		}

		public static Map<Columns, JDBCType> getColumnTypeMap() {
			return COLUMN_TYPE_MAP;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((courses == null) ? 0 : courses.hashCode());
			result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
			result = prime * result + ((departmentName == null) ? 0 : departmentName.hashCode());
			result = prime * result + ((email == null) ? 0 : email.hashCode());
			result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
			result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
			result = prime * result + ((updatedAt == null) ? 0 : updatedAt.hashCode());
			result = prime * result + ((userName == null) ? 0 : userName.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			SidebarInfo other = (SidebarInfo) obj;
			if (courses == null) {
				if (other.courses != null)
					return false;
			} else if (!courses.equals(other.courses))
				return false;
			if (createdAt == null) {
				if (other.createdAt != null)
					return false;
			} else if (!createdAt.equals(other.createdAt))
				return false;
			if (departmentName == null) {
				if (other.departmentName != null)
					return false;
			} else if (!departmentName.equals(other.departmentName))
				return false;
			if (email == null) {
				if (other.email != null)
					return false;
			} else if (!email.equals(other.email))
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
			if (roleName == null) {
				if (other.roleName != null)
					return false;
			} else if (!roleName.equals(other.roleName))
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
			return true;
		}

		@Override
		public String toString() {
			return "SidebarInfo [id=" + id + ", userName=" + userName + ", firstName=" + firstName + ", lastName="
					+ lastName + ", email=" + email + ", roleName=" + roleName + ", departmentName=" + departmentName
					+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", courses=" + courses + "]";
		}
		
		//

}
