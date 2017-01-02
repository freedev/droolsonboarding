package droolscours;
import util.OutputDisplay;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import util.KnowledgeSessionHelper;

public class TestLesson4 {
    static KieContainer kieContainer;
    KieSession sessionStatefull = null;

    @BeforeClass
    public static void beforeClass() {
        kieContainer = KnowledgeSessionHelper.createRuleBase();
    }
    @Before
    public void setUp() throws Exception {
        System.out.println("------------Before------------");
    }
    @After
    public void tearDown() throws Exception {
        System.out.println("------------After ------------");
    }

    @Test
    public void testRuleFlow1() {
        sessionStatefull = KnowledgeSessionHelper
                .getStatefulKnowledgeSessionForJBPM(kieContainer, "ksession-lesson4");
        OutputDisplay display = new OutputDisplay();
        sessionStatefull.setGlobal("showResult", display);
        Account a = new Account();
        sessionStatefull.insert(a);
        sessionStatefull.startProcess("RF1");
        sessionStatefull.fireAllRules();
    }
}