package some.thing.bpm;

import java.util.HashMap;

import org.apache.ibatis.logging.LogFactory;
import org.camunda.bpm.engine.impl.util.LogUtil;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.Deployment;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;

/**
 * Test case starting an in-memory database-backed Process Engine.
 */
public class InMemoryH2Test {

	@Rule
	public ProcessEngineRule rule = new ProcessEngineRule();

	// enable more detailed logging
	static {
		// LogUtil.readJavaUtilLoggingConfigFromClasspath(); // process engine
		// LogFactory.useJdkLogging(); // MyBatis
	}

	@Before
	public void setup() {
		init(rule.getProcessEngine());
	}

	/**
	 * Just tests if the process definition is deployable.
	 */
	@Test
	@Deployment(resources = "bpmn-letters-simple.bpmn")
	public void testBPMNSimple() {
		// nothing is done here, as we just want to check for exceptions during
		// deployment
	}

	@Test
	@Deployment(resources = "bpmn-letters-extended.bpmn")
	public void testBPMNExtended() {
		// nothing is done here, as we just want to check for exceptions during
		// deployment
	}

	@Test
	@Deployment(resources = "bpmn-letters-extended.bpmn")
	public void testB() {
		ProcessInstance processInstance = rule.getRuntimeService().startProcessInstanceByKey("bpmn-letters-extended",withParams("B"));
		assertThat(processInstance).hasPassed("ExclusiveGateway_1");
		assertThat(processInstance).isEnded();
	}
	
	@Test
	@Deployment(resources = "bpmn-letters-extended.bpmn")
	public void testP() {
		ProcessInstance processInstance = rule.getRuntimeService().startProcessInstanceByKey("bpmn-letters-extended",withParams("P"));
		assertThat(processInstance).isEnded();
		assertThat(processInstance).hasPassed("ExclusiveGateway_27");		
	}
	
	@Test
	@Deployment(resources = "bpmn-letters-extended.bpmn")
	public void testM() {
		ProcessInstance processInstance = rule.getRuntimeService().startProcessInstanceByKey("bpmn-letters-extended",withParams("M"));
		assertThat(processInstance).isEnded();
		assertThat(processInstance).hasPassed("ExclusiveGateway_18");		
	}
	@Test
	@Deployment(resources = "bpmn-letters-extended.bpmn")
	public void testN() {
		ProcessInstance processInstance = rule.getRuntimeService().startProcessInstanceByKey("bpmn-letters-extended",withParams("N"));
		assertThat(processInstance).isEnded();
		assertThat(processInstance).hasPassed("ExclusiveGateway_23");		
	}
	private HashMap<String, Object> withParams(String input) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("input", input);
		return hashMap;
	}
	
	@Test
	@Deployment(resources = "xor-example.bpmn")
	public void testXorExample() {
		// nothing is done here, as we just want to check for exceptions during
				// deployment		
	}
	
	@Test
	@Deployment(resources = "parallel-example.bpmn")
	public void testParallelExample() {
		// nothing is done here, as we just want to check for exceptions during
				// deployment
	}
	
	@Test
	@Deployment(resources = "timer-example.bpmn")
	public void testTimerExample() {
		// nothing is done here, as we just want to check for exceptions during
				// deployment
	}
}
