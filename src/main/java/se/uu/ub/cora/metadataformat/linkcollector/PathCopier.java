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

package se.uu.ub.cora.metadataformat.linkcollector;

import se.uu.ub.cora.metadataformat.data.DataAtomic;
import se.uu.ub.cora.metadataformat.data.DataElement;
import se.uu.ub.cora.metadataformat.data.DataGroup;

public final class PathCopier {
	private static final String ATTRIBUTE = "attribute";
	private static final String ATTRIBUTES = "attributes";
	private static final String NAME_IN_DATA = "nameInData";
	private static final String REPEAT_ID = "repeatId";
	private static final String LINKED_PATH = "linkedPath";

	private PathCopier() throws InstantiationException {
		throw new InstantiationException("Instances of this class are forbidden");
	}

	public static DataGroup copyPath(DataGroup pathToCopy) {
		if (pathToCopy == null) {
			return null;
		}
		DataGroup pathCopy = copyBaseDataGroup(pathToCopy);
		copyRepeatId(pathToCopy, pathCopy);
		copyAttributes(pathToCopy, pathCopy);
		copyLinkedPath(pathToCopy, pathCopy);
		return pathCopy;
	}

	private static DataGroup copyBaseDataGroup(DataGroup pathToCopy) {
		DataGroup pathCopy = DataGroup.withNameInData(LINKED_PATH);
		pathCopy.addChild(DataAtomic.withNameInDataAndValue(NAME_IN_DATA,
				pathToCopy.getFirstAtomicValueWithNameInData(NAME_IN_DATA)));
		return pathCopy;
	}

	private static void copyRepeatId(DataGroup pathToCopy, DataGroup pathCopy) {
		if (pathToCopy.containsChildWithNameInData(REPEAT_ID)) {
			pathCopy.addChild(DataAtomic.withNameInDataAndValue(REPEAT_ID,
					pathToCopy.getFirstAtomicValueWithNameInData(REPEAT_ID)));
		}
	}

	private static void copyAttributes(DataGroup pathToCopy, DataGroup pathCopy) {
		if (pathToCopy.containsChildWithNameInData(ATTRIBUTES)) {
			DataGroup attributes = DataGroup.withNameInData(ATTRIBUTES);
			pathCopy.addChild(attributes);
			for (DataElement attributeToCopy : pathToCopy.getFirstGroupWithNameInData(ATTRIBUTES)
					.getChildren()) {
				copyAttribute(attributes, attributeToCopy);
			}
		}
	}

	private static void copyAttribute(DataGroup attributes, DataElement attributeToCopy) {
		DataGroup attribute = DataGroup.withNameInData(ATTRIBUTE);
		attributes.addChild(attribute);
		for (DataElement attributePart : ((DataGroup) attributeToCopy).getChildren()) {
			attribute.addChild(DataAtomic.withNameInDataAndValue(attributePart.getNameInData(),
					((DataAtomic) attributePart).getValue()));
		}
	}

	private static void copyLinkedPath(DataGroup pathToCopy, DataGroup pathCopy) {
		if (pathToCopy.containsChildWithNameInData(LINKED_PATH)) {
			pathCopy.addChild(copyPath(pathToCopy.getFirstGroupWithNameInData(LINKED_PATH)));
		}
	}
}