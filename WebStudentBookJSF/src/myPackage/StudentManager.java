package myPackage;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class StudentManager {


	List<Student> students;
	DBUtil dbutil = new DBUtil();

	public StudentManager() { }

	public List<Student> getStudents()
	{
		return students;
	}
	public void fetchStudents() throws Exception
	{
		
		this.students= dbutil.getStudents();
	}
	public String fetchStudents(int id)
	{
		Student theStudent = dbutil.fetchStudent(id);
		ExternalContext externalContext =
		FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> requestMap =
				externalContext.getRequestMap();
		requestMap.put("student", theStudent);
		return "edit-studs";
	}


	public String addStudent(Student stu)
	{
		dbutil.addStudent(stu);
		return "list-studs";
	}
	public String updateStudent(Student student)
	{
		dbutil.updateStudent(student);
		return "list-studs";
	}
	public String deleteStudent(int id )
	{
		dbutil.deleteStudent(id);
		return "list-studs";
	}
}
