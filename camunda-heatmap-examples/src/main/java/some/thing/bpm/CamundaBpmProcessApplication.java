package some.thing.bpm;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.ProcessEngine;

/**
 * Process Application exposing this application's resources the process engine.
 */
@ProcessApplication
public class CamundaBpmProcessApplication extends ServletProcessApplication {

	/**
	 * In a @PostDeploy Hook you can interact with the process engine and access
	 * the processes the application has deployed.
	 */
	@PostDeploy
	public void onDeploymentFinished(ProcessEngine processEngine) {

		// start an initial process instance
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("name", "John");

		// parallel-example
		processEngine.getRuntimeService().startProcessInstanceByKey(
				"parallel-example", variables);

		// timer-example
		processEngine.getRuntimeService().startProcessInstanceByKey(
				"timer-example", variables);

		// bpmn-letters-simple
		processEngine.getRuntimeService().startProcessInstanceByKey(
				"bpmn-letters-simple", variables);

		// bpmn-letters-extended
		processEngine.getRuntimeService().startProcessInstanceByKey(
				"bpmn-letters-extended", withInput("B"));
		processEngine.getRuntimeService().startProcessInstanceByKey(
				"bpmn-letters-extended", withInput("B"));
		processEngine.getRuntimeService().startProcessInstanceByKey(
				"bpmn-letters-extended", withInput("B"));
		processEngine.getRuntimeService().startProcessInstanceByKey(
				"bpmn-letters-extended", withInput("B"));
		processEngine.getRuntimeService().startProcessInstanceByKey(
				"bpmn-letters-extended", withInput("P"));
		processEngine.getRuntimeService().startProcessInstanceByKey(
				"bpmn-letters-extended", withInput("P"));
		processEngine.getRuntimeService().startProcessInstanceByKey(
				"bpmn-letters-extended", withInput("P"));
		processEngine.getRuntimeService().startProcessInstanceByKey(
				"bpmn-letters-extended", withInput("M"));
		processEngine.getRuntimeService().startProcessInstanceByKey(
				"bpmn-letters-extended", withInput("M"));
		processEngine.getRuntimeService().startProcessInstanceByKey(
				"bpmn-letters-extended", withInput("N"));
		
		// xor-example
		processEngine.getRuntimeService().startProcessInstanceByKey(
				"xor-example", withInput("A"));
		
		processEngine.getRuntimeService().startProcessInstanceByKey(
				"xor-example", withInput("B"));
		
		processEngine.getRuntimeService().startProcessInstanceByKey(
				"xor-example", withInput("C"));


	}

	private Map<String,Object> withInput(final String string) {
		return new HashMap<String, Object>(){/**
			 * 
			 */
			private static final long serialVersionUID = 4596770748028426148L;

		{
			put("input", string);
		}};
	}

}
