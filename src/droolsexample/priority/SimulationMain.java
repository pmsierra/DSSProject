package droolsexample.priority;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import droolsexample.priority.SQLiteManager;



public class SimulationMain {
    public static void main(String[] args) {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.getKieClasspathContainer();

        execute(ks, kc);

    }
    public static void execute(KieServices ks, KieContainer kc) {
		SQLiteManager manager = new SQLiteManager();
		boolean everything_ok = manager.Connect();try {
			System.out.println(manager.getSqlite_connection().getWarnings());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(everything_ok + " ");
		
		SQLiteMethods methods= new SQLiteMethods(manager.getSqlite_connection());
		
		KieSession ksession = kc.newKieSession("PriorityKS");

		
		
        // Once the session is created, the application can interact with it
        
    	Resource courtains = new Resource("courtains", "LOW", 10f);
    	Resource serum = new Resource("Serum", "LOW", 20f);
    	Resource scalpel = new Resource("Scalpel", "MEDIUM", 30f);
    	Resource mnra = new Resource("MNRA", "HIGH", 50f);
    	Resource infinityStone = new Resource("Infinity Stone", "HIGH", 1000f);
    	
    	
    	LinkedList<Resource> cardiologyList = new LinkedList<Resource>();
    	cardiologyList.add(courtains);
    	cardiologyList.add(scalpel);
    	cardiologyList.add(mnra);
    	sort(cardiologyList);
    	LinkedList<Resource> neurologyList = new LinkedList<Resource>();
    	neurologyList.add(infinityStone);
    	neurologyList.add(serum);
    	neurologyList.add(scalpel);
    	sort(neurologyList);
    	LinkedList<Resource> radiologyList = new LinkedList<Resource>();
    	radiologyList.add(scalpel);
    	radiologyList.add(mnra);
    	sort(radiologyList);
    	LinkedList<Resource> obstetricsList = new LinkedList<Resource>();
    	obstetricsList.add(courtains);
    	obstetricsList.add(scalpel);
    	sort(obstetricsList);
    

    	

    	Department neurology = new Department ("Neurology",10, 20f, 80, 15, neurologyList, 0f, false );
    	Department radiology = new Department ("Radiology",10, 20f, 80, 15, radiologyList, 0f, false );
    	Department cardiology = new Department ("Cardiology",10, 20f, 80, 15, cardiologyList, 0f, false );
    	Department obstetrics = new Department ("Obstetrics",5, 10f, 60, 6, obstetricsList, 0f, false );
    
    	LinkedList<Department> laPazList = new LinkedList<Department>();
    	laPazList.add(cardiology);
    	laPazList.add(neurology);
    	laPazList.add(radiology);
    	laPazList.add(obstetrics);
    	LinkedList<Resource> purchaseList = new LinkedList<Resource>();
    	LinkedList<Department> departmentOrder = new LinkedList<Department>();
    	
    	
    	Hospital laPaz = manager.getMethods().Search_hospital_by_name("laPaz") ;
    	System.out.println(laPaz.toString());
    	System.out.println(laPaz.getHospitalList());
       
        ksession.insert(laPaz);

        
        
        // and fire the rules
        ksession.fireAllRules();


        // and then dispose the session
        ksession.dispose();

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