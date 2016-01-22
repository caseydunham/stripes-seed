package net.caseydunham.seed.stripes.action;

import net.caseydunham.seed.model.Example;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import net.sourceforge.stripes.validation.Validate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@UrlBinding(BaseActionBean.HOME_ACTION)
public class HomeActionBean extends BaseActionBean {

	private static final Logger LOG = LoggerFactory.getLogger(HomeActionBean.class);

	public static final String DEFAULT_TITLE = "Untitled";
	public static final String DEFAULT_USERNAME = "Unknown";

	private String title;
	private String username;

	@Validate(required = true)
	private String content;

	private Example example;

	@DontValidate
	@DefaultHandler
	public Resolution view() {
		LOG.info("forwarding to " + HOME_PAGE);
		return new ForwardResolution(HOME_PAGE);
	}

	@After(stages = {LifecycleStage.BindingAndValidation})
	public void bind() {
		if (StringUtils.isEmpty(getTitle())) {
			setTitle(DEFAULT_TITLE);
		}
		if (StringUtils.isEmpty(getUsername())) {
			setUsername(DEFAULT_USERNAME);
		}
	}

	@HandlesEvent("submit")
	public Resolution submit() {
		Example p = new Example();
		p.setTitle(getTitle());
		p.setUsername(getUsername());
		p.setContent(getContent());

		getExampleService().create(p);
		setExample(p);
		return new RedirectResolution(VIEW_PASTE_ACTION + "/" + p.getId());
	}

	public void setExample(Example example) { this.example = example;  }
	public Example getExample() { return example; }

	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }

	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }

	public String getContent() { return content; }
	public void setContent(String content) { this.content = content; }

}
