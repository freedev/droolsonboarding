package droolscours;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.api.runtime.rule.FactHandle;

import util.KnowledgeSessionHelper;
import util.OutputDisplay;

public class TestLesson1 {
	@Rule public TestName name = new TestName();

	StatelessKieSession sessionStateless = null;
	KieSession sessionStatefull = null;

	static KieContainer kieContainer;

	@BeforeClass
	public static void beforeClass() {
		kieContainer = KnowledgeSessionHelper.createRuleBase();
	}

    @Before
    public void setUp() throws Exception{
        System.out.println("------------Before " + name.getMethodName() + "------------");
    }
    @After
    public void tearDown() throws Exception{
        System.out.println("------------After " + name.getMethodName() + "------------");
    }

	@Test
	public void testFirstOne() {
		sessionStatefull = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "ksession-rules");

		Account a = new Account();
		sessionStatefull.setGlobal("showResults", new OutputDisplay());
		sessionStatefull.insert(a);
		
		sessionStatefull.fireAllRules();
		
	}

	@Test
	public void testRuleOneFactWithFactAndUsageOfGlobalAndCallBack() {
		sessionStatefull = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "ksession-rules");
		sessionStatefull.addEventListener(new RuleRuntimeEventListener() {
			
			@Override
			public void objectUpdated(ObjectUpdatedEvent arg0) {
				System.out.println("Object updated \n" + arg0.getObject().toString());
			}
			
			@Override
			public void objectInserted(ObjectInsertedEvent arg0) {
				System.out.println("Object inserted \n" + arg0.getObject().toString());
			}
			
			@Override
			public void objectDeleted(ObjectDeletedEvent arg0) {
				System.out.println("Object deleted \n" + arg0.getOldObject().toString());
			}
		});
		Account a = new Account();
		a.setAccountNo(10);
		FactHandle handlea = sessionStatefull.insert(a);
		a.setBalance(12.0);
		sessionStatefull.update(handlea, a);
		sessionStatefull.delete(handlea);
		sessionStatefull.fireAllRules();
		System.out.println("printing something");
	}
	
	@Test
	public void testFirstOneTwoFireAllRules() {
		sessionStatefull = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "ksession-rules");

		Account a = new Account();
		sessionStatefull.setGlobal("showResults", new OutputDisplay());
		sessionStatefull.insert(a);
		
		System.out.println("First fire all rules");
		sessionStatefull.fireAllRules();
		
		System.out.println("Second fire all rules");
		sessionStatefull.fireAllRules();
	}

	@Test
	public void testFirstOneTwoFireAllRulesAndASetter() {
		sessionStatefull = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "ksession-rules");

		Account a = new Account();
		sessionStatefull.setGlobal("showResults", new OutputDisplay());
		sessionStatefull.insert(a);
		
		System.out.println("First fire all rules");
		sessionStatefull.fireAllRules();
		a.setAccountNo(1);
		System.out.println("Second fire all rules");
		sessionStatefull.fireAllRules();
	}

	@Test
	public void testFirstOneTwoFireAllRulesWithUpdateInBetween() {
		sessionStatefull = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "ksession-rules");

		Account a = new Account();
		sessionStatefull.setGlobal("showResults", new OutputDisplay());
		FactHandle handlea = sessionStatefull.insert(a);
		
		System.out.println("First fire all rules");
		sessionStatefull.fireAllRules();
		sessionStatefull.update(handlea, a);
		System.out.println("Second fire all rules");
		sessionStatefull.fireAllRules();
	}

	@Test
	public void testRuleOneFacThatInsertObject() {
		sessionStatefull = KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "ksession-rules");
		sessionStatefull.setGlobal("showResults", new OutputDisplay());

		sessionStatefull.addEventListener(new RuleRuntimeEventListener() {
			
			@Override
			public void objectUpdated(ObjectUpdatedEvent arg0) {
				System.out.println("Object updated \n" + arg0.getObject().toString());
			}
			
			@Override
			public void objectInserted(ObjectInsertedEvent arg0) {
				System.out.println("Object inserted \n" + arg0.getObject().toString());
			}
			
			@Override
			public void objectDeleted(ObjectDeletedEvent arg0) {
				System.out.println("Object deleted \n" + arg0.getOldObject().toString());
			}
		});
		CashFlow a = new CashFlow();
		sessionStatefull.insert(a);
		sessionStatefull.fireAllRules();
	}

}
