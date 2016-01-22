package net.caseydunham.seed;

import net.caseydunham.seed.dao.ExampleDao;
import net.caseydunham.seed.model.Example;
import net.caseydunham.seed.services.ExampleService;
import net.caseydunham.seed.services.impl.ExampleServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class TestExampleService extends BaseTest {

	private ExampleService exampleService;

	@Mock
	private ExampleDao exampleDao;

	@Before
	public void setUp() {
		exampleService = new ExampleServiceImpl();
		exampleService.setExampleDao(exampleDao);
	}

	@Test
	public void testCreatePaste() {
		Example p = new Example();
		exampleService.create(p);
		verify(exampleDao).saveOrUpdate(p);
		assertNotNull(p);
		assertNotNull(p.getCreated());
	}

	@Test
	public void testGetPasteById() {
		Example found = new Example();
		found.setId(999L);
		Date created = new Date();
		found.setCreated(created);
		found.setTitle("Test");
		found.setUsername("TestUser");
		found.setContent("This is a test");
		when(exampleDao.getById(999L)).thenReturn(found);

		Example p = exampleService.getById(999L);
		assertNotNull(p);
		assertEquals(new Long(999L), p.getId());
		assertEquals(created, p.getCreated());
		assertEquals("Test", p.getTitle());
		assertEquals("TestUser", p.getUsername());
		assertEquals("This is a test", p.getContent());
	}

}
