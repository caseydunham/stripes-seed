package net.caseydunham.seed.stripes.action;

import net.caseydunham.seed.model.Example;
import net.sourceforge.stripes.action.*;
import net.sourceforge.stripes.controller.LifecycleStage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@UrlBinding(BaseActionBean.VIEW_PASTE_ACTION + "/{id}")
public class ViewExampleActionBean extends BaseActionBean {

	private static final Logger LOG = LoggerFactory.getLogger(ViewExampleActionBean.class);

	private Long id;
	private Example example;

	@After(stages = {LifecycleStage.BindingAndValidation})
	public void bind() {
		if (getId() != null) {
			Example p = getExampleService().getById(getId());
			setExample(p);
		}
	}

	@DontValidate
	@DefaultHandler
	public Resolution view() {
		LOG.info("forwarding to " + VIEW_PASTE_PAGE);
		return new ForwardResolution(VIEW_PASTE_PAGE);
	}

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }

	public Example getExample() { return example; }
	public void setExample(Example example) { this.example = example; }

}
