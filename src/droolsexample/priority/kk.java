package droolsexample.priority;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class kk {

public static void main(String[] args) throws SQLException {
		
		SQLiteManager manager = new SQLiteManager();
		boolean everything_ok = manager.Connect();
		boolean tables_ok = manager.CreateTables();
		System.out.println(manager.getSqlite_connection().getWarnings());
		
		System.out.println(everything_ok + " ");
		
		SQLiteMethods methods= new SQLiteMethods(manager.getSqlite_connection());
		
		// -----Inserts----------------------
		
		Department neurology = new Department ("Neurology",10, 20f, 80, 15, false,1 );
    	Department radiology = new Department ("Radiology",20, 20f, 100, 30, false,2 );
    	Department cardiology = new Department ("Cardiology",10, 20f, 80, 15,false,3 );
    	Department obstetrics = new Department ("Obstetrics",5, 10f, 60, 6, false,4);

		
		Resource courtains = new Resource("courtains", "LOW", 10f);
    	Resource serum = new Resource("Serum", "LOW", 20f);
    	Resource scalpel = new Resource("Scalpel", "MEDIUM", 30f);
    	Resource mnra = new Resource("MNRA", "HIGH", 50f);
    	Resource infinityStone = new Resource("Infinity Stone", "HIGH", 1000f);
		methods.Insert_new_resource(courtains);
		methods.Insert_new_resource(serum);
		methods.Insert_new_resource(scalpel);
		methods.Insert_new_resource(mnra);
		methods.Insert_new_resource(infinityStone);
		
		Hospital laPaz = new Hospital("laPaz", 2000f ); 
		methods.Insert_new_hospital(laPaz);
		
		//meter listas:
		LinkedList<Resource> radiologyList = new LinkedList<Resource>();
		radiologyList.add(courtains);
		radiologyList.add(scalpel);
		radiologyList.add(mnra);
    	sort(radiologyList); 
    	LinkedList<Resource> neurologyList = new LinkedList<Resource>();
    	neurologyList.add(infinityStone);
    	neurologyList.add(serum);
    	neurologyList.add(scalpel);
    	sort(neurologyList);
    	LinkedList<Resource> cardiologyList = new LinkedList<Resource>();
    	cardiologyList.add(courtains);
    	cardiologyList.add(scalpel);
    	cardiologyList.add(mnra);
    	sort(cardiologyList);
    	LinkedList<Resource> obstetricsList = new LinkedList<Resource>();
    	obstetricsList.add(courtains);
    	obstetricsList.add(scalpel);
    	sort(obstetricsList);
    	
    	neurology.setWishlistshopping(neurologyList);
    	radiology.setWishlistshopping(radiologyList);
    	cardiology.setWishlistshopping(cardiologyList);
    	obstetrics.setWishlistshopping(obstetricsList);
    	
		methods.Insert_new_department(neurology);
		methods.Insert_new_department(radiology);
		methods.Insert_new_department(cardiology);
		methods.Insert_new_department(obstetrics);
    	
    	LinkedList<Department> laPazList = new LinkedList<Department>();
    	laPazList.add(cardiology);
    	laPazList.add(neurology);
    	laPazList.add(radiology);
    	laPazList.add(obstetrics);
    	
    	laPaz.setHospitalList(laPazList);
    	
    	
    	
    
		
		//---List----------------------------
    	List<Department> Lista = methods.List_all_departments();
    	System.out.println(Lista);
		List<Resource> Lista2 = methods.List_all_resources();
		System.out.println(Lista2);
		
		// -----Updates---------------------------
		radiology.setAvghours(30);
		radiology.setNemployees(70);
		methods.Update_department(radiology);
		
		courtains.setPrice(69f);
		courtains.setPriority("HIGH");
		System.out.println(methods.Update_resource(courtains));
		
		laPaz.setBudget(3000f);
		
		//------- Delete---------------
		methods.Delete_department(neurology);
		methods.Delete_resource(serum);
		methods.Delete_departmentresource(neurology, infinityStone);
		
		//----------Search-------------------
		methods.Search_department_by_name("Cardiology");
		methods.Search_hospital_by_name("laPaz");
		methods.Search_resource_by_name("Infinity Stone");
		
	}
private static void sort(LinkedList<Resource> list) {
	Collections.sort(list, new Comparator<Resource>() {
	@Override
		public int compare(Resource o1, Resource o2) {
            if (o1.getPriority() == o2.getPriority()) {
                return o1.getName().compareTo(o2.getName());
            } else {
                return o1.getPriority().compareTo(o2.getPriority());
            }
        }
		});
}

}