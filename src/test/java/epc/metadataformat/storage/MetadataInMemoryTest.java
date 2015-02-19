package epc.metadataformat.storage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.testng.annotations.Test;

import epc.metadataformat.CoherentMetadata;
import epc.metadataformat.testdata.TestDataMetadataInMemoryStorage;

public class MetadataInMemoryTest {
	
	@Test
	public void testInit() {
		MetadataInMemoryStorage metadataStorageGateway = new MetadataInMemoryStorage();
		assertNotNull(metadataStorageGateway.getAllMetadata());
	}

	@Test
	public void testInitWithNull() {
		MetadataInMemoryStorage metadataStorageGateway = new MetadataInMemoryStorage(
				null);
		assertNotNull(metadataStorageGateway.getAllMetadata());
	}

	@Test
	public void testInitWithTestData() {
		MetadataStorageGateway metadataStorageGateway = TestDataMetadataInMemoryStorage
				.createMetadataInMemoryStorageContainingTestData();
		CoherentMetadata coherentMetadata = metadataStorageGateway
				.getAllMetadata();
		assertNotNull(coherentMetadata, "Metadata should be present");
		assertEquals(coherentMetadata.getTextTranslation("sv", "textId"),
				"Testar en text");
	}

}
