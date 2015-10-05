package se.uu.ub.cora.metadataformat.validator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import se.uu.ub.cora.metadataformat.data.DataAtomic;
import se.uu.ub.cora.metadataformat.data.DataGroup;
import se.uu.ub.cora.metadataformat.storage.MetadataStorage;

public class MetadataStorageStub implements MetadataStorage {

	@Override
	public Collection<DataGroup> getMetadataElements() {
		List<DataGroup> dataGroups = new ArrayList<>();

		// textVar2
		DataGroup textVar2 = DataGroup.withNameInData("metadata");
		textVar2.addAttributeByIdWithValue("type", "textVariable");

		DataGroup textVar2RecordInfo = DataGroup.withNameInData("recordInfo");
		textVar2RecordInfo.addChild(DataAtomic.withNameInDataAndValue("id", "textVar2"));
		textVar2.addChild(textVar2RecordInfo);

		textVar2.addChild(DataAtomic.withNameInDataAndValue("nameInData", "textVar2"));
		textVar2.addChild(DataAtomic.withNameInDataAndValue("textId", "textVarText"));
		textVar2.addChild(DataAtomic.withNameInDataAndValue("defTextId", "textVarDefText"));
		textVar2.addChild(DataAtomic.withNameInDataAndValue("regEx",
				"((^(([0-1][0-9])|([2][0-3])):[0-5][0-9]$)|^$){1}"));
		dataGroups.add(textVar2);

		// groupTypeVar
		DataGroup groupTypeVar = DataGroup.withNameInData("metadata");
		groupTypeVar.addAttributeByIdWithValue("type", "collectionVariable");

		DataGroup groupTypeVarRecordInfo = DataGroup.withNameInData("recordInfo");
		groupTypeVarRecordInfo.addChild(DataAtomic.withNameInDataAndValue("id", "groupTypeVar"));
		groupTypeVar.addChild(groupTypeVarRecordInfo);

		groupTypeVar.addChild(DataAtomic.withNameInDataAndValue("nameInData", "groupTypeVar"));
		groupTypeVar.addChild(DataAtomic.withNameInDataAndValue("textId", "groupTypeVarText"));
		groupTypeVar.addChild(DataAtomic.withNameInDataAndValue("defTextId", "groupTypeVarDefText"));
		groupTypeVar.addChild(DataAtomic.withNameInDataAndValue("refCollectionId",
				"groupTypeCollection"));
		dataGroups.add(groupTypeVar);

		// groupType1
		DataGroup groupType1 = DataGroup.withNameInData("metadata");
		groupType1.addAttributeByIdWithValue("type", "collectionItem");

		DataGroup groupType1RecordInfo = DataGroup.withNameInData("recordInfo");
		groupType1RecordInfo.addChild(DataAtomic.withNameInDataAndValue("id", "groupType1"));
		groupType1.addChild(groupType1RecordInfo);

		groupType1.addChild(DataAtomic.withNameInDataAndValue("nameInData", "groupType1"));
		groupType1.addChild(DataAtomic.withNameInDataAndValue("textId", "groupType1Text"));
		groupType1.addChild(DataAtomic.withNameInDataAndValue("defTextId", "groupType1DefText"));
		dataGroups.add(groupType1);

		// groupType2
		DataGroup groupType2 = DataGroup.withNameInData("metadata");
		groupType2.addAttributeByIdWithValue("type", "collectionItem");

		DataGroup groupType2RecordInfo = DataGroup.withNameInData("recordInfo");
		groupType2RecordInfo.addChild(DataAtomic.withNameInDataAndValue("id", "groupType2"));
		groupType2.addChild(groupType2RecordInfo);

		groupType2.addChild(DataAtomic.withNameInDataAndValue("nameInData", "groupType2"));
		groupType2.addChild(DataAtomic.withNameInDataAndValue("textId", "groupType2Text"));
		groupType2.addChild(DataAtomic.withNameInDataAndValue("defTextId", "groupType2DefText"));
		dataGroups.add(groupType2);

		// groupTypeCollection
		DataGroup groupTypeCollection = DataGroup.withNameInData("metadata");
		groupTypeCollection.addAttributeByIdWithValue("type", "itemCollection");

		DataGroup groupTypeCollectionRecordInfo = DataGroup.withNameInData("recordInfo");
		groupTypeCollectionRecordInfo.addChild(DataAtomic.withNameInDataAndValue("id",
				"groupTypeCollection"));
		groupTypeCollection.addChild(groupTypeCollectionRecordInfo);

		groupTypeCollection
				.addChild(DataAtomic.withNameInDataAndValue("nameInData", "groupTypeCollection"));
		groupTypeCollection.addChild(DataAtomic.withNameInDataAndValue("textId",
				"groupTypeCollectionText"));
		groupTypeCollection.addChild(DataAtomic.withNameInDataAndValue("defTextId",
				"groupTypeCollectionDefText"));

		DataGroup collectionItemReferences = DataGroup.withNameInData("collectionItemReferences");
		collectionItemReferences.addChild(DataAtomic.withNameInDataAndValue("ref", "groupType1"));
		collectionItemReferences.addChild(DataAtomic.withNameInDataAndValue("ref", "groupType2"));
		groupTypeCollection.addChild(collectionItemReferences);

		dataGroups.add(groupTypeCollection);

		// child (textVar)
		DataGroup textVar = DataGroup.withNameInData("metadata");
		textVar.addAttributeByIdWithValue("type", "textVariable");

		DataGroup textVarRecordInfo = DataGroup.withNameInData("recordInfo");
		textVarRecordInfo.addChild(DataAtomic.withNameInDataAndValue("id", "textVarId"));
		textVar.addChild(textVarRecordInfo);

		textVar.addChild(DataAtomic.withNameInDataAndValue("nameInData", "textVarNameInData"));
		textVar.addChild(DataAtomic.withNameInDataAndValue("textId", "textVarText"));
		textVar.addChild(DataAtomic.withNameInDataAndValue("defTextId", "textVarDefText"));
		textVar.addChild(DataAtomic.withNameInDataAndValue("regEx",
				"((^(([0-1][0-9])|([2][0-3])):[0-5][0-9]$)|^$){1}"));

		dataGroups.add(textVar);

		// group
		DataGroup group = DataGroup.withNameInData("metadata");
		group.addAttributeByIdWithValue("type", "group");

		DataGroup groupRecordInfo = DataGroup.withNameInData("recordInfo");
		groupRecordInfo.addChild(DataAtomic.withNameInDataAndValue("id", "group"));
		group.addChild(groupRecordInfo);

		group.addChild(DataAtomic.withNameInDataAndValue("nameInData", "group"));
		group.addChild(DataAtomic.withNameInDataAndValue("textId", "groupTextId"));
		group.addChild(DataAtomic.withNameInDataAndValue("defTextId", "groupDefText"));

		DataGroup attributeReferences = DataGroup.withNameInData("attributeReferences");
		attributeReferences.addChild(DataAtomic.withNameInDataAndValue("ref", "groupTypeVar"));
		group.addChild(attributeReferences);

		DataGroup childReferences = DataGroup.withNameInData("childReferences");
		group.addChild(childReferences);

		DataGroup childReference = DataGroup.withNameInData("childReference");
		childReference.addChild(DataAtomic.withNameInDataAndValue("ref", "textVarId"));
		childReference.addChild(DataAtomic.withNameInDataAndValue("repeatMin", "1"));
		// childReference.addChild(DataAtomic.withNameInDataAndValue("repeatMinKey", "SOME_KEY"));
		childReference.addChild(DataAtomic.withNameInDataAndValue("repeatMax", "15"));
		// childReference.addChild(DataAtomic.withNameInDataAndValue("secret", "true"));
		// childReference.addChild(DataAtomic.withNameInDataAndValue("secretKey", "SECRET_KEY"));
		// childReference.addChild(DataAtomic.withNameInDataAndValue("readOnly", "true"));
		// childReference.addChild(DataAtomic.withNameInDataAndValue("readOnlyKey", "READONLY_KEY"));
		childReferences.addChild(childReference);

		dataGroups.add(group);

		// collection
		DataGroup colVar2 = DataGroup.withNameInData("metadata");
		colVar2.addAttributeByIdWithValue("type", "collectionVariable");

		DataGroup recordInfo = DataGroup.withNameInData("recordInfo");
		recordInfo.addChild(DataAtomic.withNameInDataAndValue("id", "collectionVar2"));
		colVar2.addChild(recordInfo);

		colVar2.addChild(DataAtomic.withNameInDataAndValue("nameInData", "collectionVar2"));
		colVar2.addChild(DataAtomic.withNameInDataAndValue("textId", "collectionVarText"));
		colVar2.addChild(DataAtomic.withNameInDataAndValue("defTextId", "collectionVarDefText"));
		colVar2.addChild(DataAtomic
				.withNameInDataAndValue("refCollectionId", "authorityTypeCollection"));
		dataGroups.add(colVar2);

		// itemCollection
		DataGroup authority = DataGroup.withNameInData("metadata");
		authority.addAttributeByIdWithValue("type", "itemCollection");

		DataGroup authorityRecordInfo = DataGroup.withNameInData("recordInfo");
		authorityRecordInfo
				.addChild(DataAtomic.withNameInDataAndValue("id", "authorityTypeCollection"));
		authority.addChild(authorityRecordInfo);

		authority.addChild(DataAtomic.withNameInDataAndValue("nameInData", "authorityTypeCollection"));
		authority
				.addChild(DataAtomic.withNameInDataAndValue("textId", "authorityTypeCollectionTextId"));
		authority.addChild(DataAtomic.withNameInDataAndValue("defTextId",
				"authorityTypeCollectionDefTextId"));

		DataGroup authorityItemReferences = DataGroup.withNameInData("collectionItemReferences");
		authorityItemReferences.addChild(DataAtomic.withNameInDataAndValue("ref", "person"));
		authorityItemReferences.addChild(DataAtomic.withNameInDataAndValue("ref", "family"));
		authorityItemReferences.addChild(DataAtomic.withNameInDataAndValue("ref", "organisation"));
		authorityItemReferences.addChild(DataAtomic.withNameInDataAndValue("ref", "place"));
		authority.addChild(authorityItemReferences);
		dataGroups.add(authority);

		// personItem
		DataGroup personItem = DataGroup.withNameInData("metadata");
		personItem.addAttributeByIdWithValue("type", "collectionItem");

		DataGroup personRecordInfo = DataGroup.withNameInData("recordInfo");
		personRecordInfo.addChild(DataAtomic.withNameInDataAndValue("id", "person"));
		personItem.addChild(personRecordInfo);

		personItem.addChild(DataAtomic.withNameInDataAndValue("nameInData", "person"));
		personItem.addChild(DataAtomic.withNameInDataAndValue("textId", "personTextId"));
		personItem.addChild(DataAtomic.withNameInDataAndValue("defTextId", "personDefTextId"));
		dataGroups.add(personItem);

		// familyItem
		DataGroup familyItem = DataGroup.withNameInData("metadata");
		familyItem.addAttributeByIdWithValue("type", "collectionItem");

		DataGroup familyRecordInfo = DataGroup.withNameInData("recordInfo");
		familyRecordInfo.addChild(DataAtomic.withNameInDataAndValue("id", "family"));
		familyItem.addChild(familyRecordInfo);

		familyItem.addChild(DataAtomic.withNameInDataAndValue("nameInData", "family"));
		familyItem.addChild(DataAtomic.withNameInDataAndValue("textId", "familyTextId"));
		familyItem.addChild(DataAtomic.withNameInDataAndValue("defTextId", "familyDefTextId"));
		dataGroups.add(familyItem);

		// organisationItem
		DataGroup organisationItem = DataGroup.withNameInData("metadata");
		organisationItem.addAttributeByIdWithValue("type", "collectionItem");

		DataGroup organisationRecordInfo = DataGroup.withNameInData("recordInfo");
		organisationRecordInfo.addChild(DataAtomic.withNameInDataAndValue("id", "organisation"));
		organisationItem.addChild(organisationRecordInfo);

		organisationItem.addChild(DataAtomic.withNameInDataAndValue("nameInData", "organisation"));
		organisationItem.addChild(DataAtomic.withNameInDataAndValue("textId", "organisationTextId"));
		organisationItem.addChild(DataAtomic.withNameInDataAndValue("defTextId",
				"organisationDefTextId"));
		dataGroups.add(organisationItem);

		// placeItem
		DataGroup placeItem = DataGroup.withNameInData("metadata");
		placeItem.addAttributeByIdWithValue("type", "collectionItem");

		DataGroup placeRecordInfo = DataGroup.withNameInData("recordInfo");
		placeRecordInfo.addChild(DataAtomic.withNameInDataAndValue("id", "place"));
		placeItem.addChild(placeRecordInfo);

		placeItem.addChild(DataAtomic.withNameInDataAndValue("nameInData", "place"));
		placeItem.addChild(DataAtomic.withNameInDataAndValue("textId", "placeTextId"));
		placeItem.addChild(DataAtomic.withNameInDataAndValue("defTextId", "placeDefTextId"));
		dataGroups.add(placeItem);

		return dataGroups;
	}

	@Override
	public Collection<DataGroup> getPresentationElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<DataGroup> getTexts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<DataGroup> getRecordTypes() {
		// TODO Auto-generated method stub
		return null;
	}

}