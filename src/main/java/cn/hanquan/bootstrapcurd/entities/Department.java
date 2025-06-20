package cn.hanquan.bootstrapcurd.entities;

import javax.persistence.*;

@Entity
@Table(name = "department")
public class Department {

	@Id //这是一个主键
	@GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
	private Integer id;
	@Column(name = "departmentName", length = 256)
	private String departmentName;

	public Department() {
	}
	
	public Department(int i, String string) {
		this.id = i;
		this.departmentName = string;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", departmentName=" + departmentName + "]";
	}
	
}
