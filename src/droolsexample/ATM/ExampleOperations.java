/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package droolsexample.ATM;

import droolsexample.Message;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 *
 * @author alberto.gildelafuent
 */
public class ExampleOperations {

    public static void main(String[] args) {
        KieServices ks = KieServices.Factory.get();
        KieContainer kc = ks.getKieClasspathContainer();

        execute(kc);
    }

    public static void execute(KieContainer kc) {
        // From the container, a session is created based on
        // its definition and configuration in the META-INF/kmodule.xml file
        KieSession ksession = kc.newKieSession("HelloWorldKS");

        // Once the session is created, the application can interact with it
        // To set up a ThreadedFileLogger, so that the audit view reflects events whilst debugging,
        // uncomment the next line
        // KieRuntimeLogger logger = ks.getLoggers().newThreadedFileLogger( ksession, "./helloworld", 1000 );
        // The application can insert facts into the session
        Operation op1 = new Operation(1, true, false, true, Tries.EXPIRED, 100, 1000, 500);
        Operation op2 = new Operation(2, true, false, true, Tries.NOT_EXPIRED, 100, 1000, 500);
        Operation op3 = new Operation(3, true, false, true, Tries.NOT_EXPIRED, 1000, 100, 500);
        Operation op4 = new Operation(4, true, false, true, Tries.NOT_EXPIRED, 1000, 10000, 500);
        Operation op5 = new Operation(5, true, true, true, Tries.EXPIRED, 100, 1000, 500);
        Operation op6 = new Operation(6, false, true, true, Tries.NOT_EXPIRED, 100, 1000, 500);
        Operation op7 = new Operation(6, false, true, true, Tries.NOT_EXPIRED, 100, 1000, 500);
        
        System.out.println(op1);
        System.out.println(op2);
        System.out.println(op3);
        System.out.println(op4);
        System.out.println(op5);
        System.out.println(op6);
        System.out.println(op7);
         

        ksession.insert(op1);
        ksession.insert(op2);
        ksession.insert(op3);
        ksession.insert(op4);
        ksession.insert(op5);
        ksession.insert(op6);
        ksession.insert(op7);

        Message message = new Message();
        message.setMessage("Hello World");
        message.setStatus(Message.HELLO);
        ksession.insert(message);
        // and fire the rules
        ksession.fireAllRules();

        // The objects have been already modified. 
        
        // AFTER Triggering the rules, the attribute authorized of some operations have been modified
        System.out.println(op1);
        System.out.println(op2);
        System.out.println(op3);
        System.out.println(op4);
        System.out.println(op5);
        System.out.println(op6);
        System.out.println(op7);

        // and then dispose the session
        ksession.dispose();
    }
}
