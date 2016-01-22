package net.caseydunham.seed;

import net.caseydunham.seed.stripes.action.ViewExampleActionBean;
import net.sourceforge.stripes.mock.MockRoundtrip;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestViewExampleActionBean extends BaseTest {

	@Test
	public void testViewActionBeanSetsRightPage() throws Exception {
		MockRoundtrip trip = new MockRoundtrip(mockServletContext, ViewExampleActionBean.class, mockHttpSession);
		trip.execute();
		assertEquals("/WEB-INF/jsps/ViewExample.jsp", trip.getDestination());
	}

	@Test
	public void testViewSetsCorrectPaste() throws Exception {
		MockRoundtrip trip = new MockRoundtrip(mockServletContext, ViewExampleActionBean.class, mockHttpSession);
		trip.setParameter("id", "1");
		trip.execute();

		ViewExampleActionBean bean = trip.getActionBean(ViewExampleActionBean.class);
		assertEquals(new Long(1), bean.getId());
	}

}
