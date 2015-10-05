package se.uu.ub.cora.metadataformat.metadata.converter;

import se.uu.ub.cora.metadataformat.data.DataGroup;
import se.uu.ub.cora.metadataformat.metadata.CollectionItem;

public final class DataGroupToCollectionItemConverter implements DataGroupToMetadataConverter {

	private DataGroup dataGroup;

	public static DataGroupToCollectionItemConverter fromDataGroup(DataGroup dataGroup) {
		return new DataGroupToCollectionItemConverter(dataGroup);
	}

	private DataGroupToCollectionItemConverter(DataGroup dataGroup) {
		this.dataGroup = dataGroup;
	}

	@Override
	public CollectionItem toMetadata() {
		DataGroup recordInfo = dataGroup.getFirstGroupWithNameInData("recordInfo");
		String id = recordInfo.getFirstAtomicValueWithNameInData("id");
		String nameInData = dataGroup.getFirstAtomicValueWithNameInData("nameInData");
		String textId = dataGroup.getFirstAtomicValueWithNameInData("textId");
		String defTextId = dataGroup.getFirstAtomicValueWithNameInData("defTextId");

		return new CollectionItem(id, nameInData, textId, defTextId);
	}

}