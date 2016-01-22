package net.caseydunham.seed.stripes.action;

import com.google.inject.Inject;
import net.caseydunham.seed.model.Example;
import net.caseydunham.seed.services.ExampleService;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;

import java.util.List;

public class BaseActionBean implements ActionBean {

	public static final String PAGE_LOCATION = "/WEB-INF/jsps";

	public static final String HOME_ACTION = "/home";
	public static final String HOME_PAGE = PAGE_LOCATION + "/Home.jsp";

	public static final String VIEW_PASTE_ACTION = "/examples";
	public static final String VIEW_PASTE_PAGE = PAGE_LOCATION + "/ViewExample.jsp";

	@Inject
	private ExampleService exampleService;
	private ActionBeanContext context;

	public ExampleService getExampleService() { return exampleService; }
	public void setExampleService(ExampleService exampleService) { this.exampleService = exampleService; }

	public List<Example> getRecentExamples() {
		return getExampleService().getRecentExamples();
	}

	public void setContext(ActionBeanContext context) { this.context = context;	}
	public ActionBeanContext getContext() { return context; }

}
