package com.eanurag.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.eanurag.impl.DataBaseManager;
import com.eanurag.objects.URL;

public class TestDataBaseManager {

	DataBaseManager dbManager = DataBaseManager.getInstance();
	private static final String SELECT_ALL_RECORDS = "SELECT * FROM `crawly`.`url`";
	URL url = new URL();

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetDBConnection() {
		assertNotEquals(null, dbManager.getDBConnection());
	}

	@Test
	public final void testReadData() {
		assertNotEquals(null, dbManager.readData(SELECT_ALL_RECORDS));
	}

	@Test
	public final void testWriteData() {
		url.setURL("http://www.lameassdomain.something.wtf.whatever.dude.com");
		assertEquals(true, dbManager.writeData(url));
	}

}
