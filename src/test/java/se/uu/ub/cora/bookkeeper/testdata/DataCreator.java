/*
 * Copyright 2015 Uppsala University Library
 *
 * This file is part of Cora.
 *
 *     Cora is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Cora is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Cora.  If not, see <http://www.gnu.org/licenses/>.
 */

package se.uu.ub.cora.bookkeeper.testdata;

import se.uu.ub.cora.bookkeeper.metadata.CollectionItem;
import se.uu.ub.cora.bookkeeper.metadata.CollectionVariable;
import se.uu.ub.cora.bookkeeper.metadata.ItemCollection;
import se.uu.ub.cora.bookkeeper.metadata.MetadataChildReference;
import se.uu.ub.cora.bookkeeper.metadata.MetadataGroup;
import se.uu.ub.cora.bookkeeper.metadata.MetadataHolder;
import se.uu.ub.cora.bookkeeper.metadata.RecordLink;
import se.uu.ub.cora.bookkeeper.metadata.RecordRelation;
import se.uu.ub.cora.bookkeeper.metadata.TextVariable;

public class DataCreator {
	public static MetadataGroup createMetaDataGroup(String id, MetadataHolder metadataHolder) {
		MetadataGroup group = MetadataGroup.withIdAndNameInDataAndTextIdAndDefTextId(id + "GroupId",
				id + "GroupNameInData", id + "GroupText", id + "GroupDefText");
		metadataHolder.addMetadataElement(group);
		return group;
	}

	public static RecordRelation createRecordRelation(String id, MetadataHolder metadataHolder) {
		RecordRelation recordRelation = RecordRelation.withIdAndNameInDataAndTextIdAndDefTextId(
				id + "RecordRelationId", id + "RecordRelationNameInData", id + "RecordRelationText",
				id + "RecordRelationDefText");
		metadataHolder.addMetadataElement(recordRelation);
		return recordRelation;
	}

	public static MetadataGroup createMetaDataGroupWithIdAndNameInNData(String id,
			String nameInData, MetadataHolder metadataHolder) {
		MetadataGroup group = MetadataGroup.withIdAndNameInDataAndTextIdAndDefTextId(id, nameInData,
				id + "GroupText", id + "GroupDefText");
		metadataHolder.addMetadataElement(group);
		return group;
	}

	public static void addDataGroupAsMetadataChildReferenceToParent(MetadataGroup child,
			MetadataGroup parent) {
		MetadataChildReference linkChild = MetadataChildReference
				.withReferenceIdAndRepeatMinAndRepeatMax(child.getId(), 1, 1);
		parent.addChildReference(linkChild);
	}

	public static void addRecordLinkChildReferenceToGroup(String id, MetadataGroup group,
			MetadataHolder metadataHolder) {
		RecordLink recordLink = RecordLink
				.withIdAndNameInDataAndTextIdAndDefTextIdAndLinkedRecordType(id + "Id",
						id + "NameInData", id + "Text", id + "DefText",
						"recordLinkLinkedRecordType");
		metadataHolder.addMetadataElement(recordLink);

		MetadataChildReference linkChild = MetadataChildReference
				.withReferenceIdAndRepeatMinAndRepeatMax(id + "Id", 1, 1);

		group.addChildReference(linkChild);
	}

	public static void addUnlimitedTextVarChildReferenceToGroup(String id, MetadataGroup group,
			MetadataHolder metadataHolder) {
		addTextVarChildReferenceToGroup(id, MetadataChildReference.UNLIMITED, group,
				metadataHolder);

	}

	public static void addOnlyOneTextVarChildReferenceToGroup(String id, MetadataGroup group,
			MetadataHolder metadataHolder) {
		addTextVarChildReferenceToGroup(id, 1, group, metadataHolder);
	}

	public static void addTextVarChildReferenceToGroup(String id, int repeatMax,
			MetadataGroup group, MetadataHolder metadataHolder) {
		TextVariable textVar = TextVariable
				.withIdAndNameInDataAndTextIdAndDefTextIdAndRegularExpression(id + "Id",
						id + "NameInData", id + "Text", id + "DefText",
						"((^(([0-1][0-9])|([2][0-3])):[0-5][0-9]$)|^$){1}");
		metadataHolder.addMetadataElement(textVar);

		MetadataChildReference groupChild = MetadataChildReference
				.withReferenceIdAndRepeatMinAndRepeatMax(id + "Id", 1, repeatMax);
		group.addChildReference(groupChild);
	}

	public static void addDefaultCollectionTwoChoices(String id, MetadataGroup group,
			MetadataHolder metadataHolder) {
		CollectionVariable colVar = new CollectionVariable(id + "Id", id + "NameInData",
				id + "Text", id + "DefText", "collectionId");
		metadataHolder.addMetadataElement(colVar);
		group.addAttributeReference(id + "Id");

		if (metadataHolder.getMetadataElement("choice1Id") == null) {
			CollectionItem choice1 = new CollectionItem("choice1Id", "choice1NameInData",
					"choice1TextId", "choice1DefTextId");
			metadataHolder.addMetadataElement(choice1);

			CollectionItem choice2 = new CollectionItem("choice2Id", "choice2NameInData",
					"choice2TextId", "choice2DefTextId");
			metadataHolder.addMetadataElement(choice2);

			ItemCollection collection = new ItemCollection("collectionId", "collectionNameInData",
					"CollectionTextId", "collectionDefTextId");
			metadataHolder.addMetadataElement(collection);
			collection.addItemReference("choice1Id");
			collection.addItemReference("choice2Id");
		}

	}
}
