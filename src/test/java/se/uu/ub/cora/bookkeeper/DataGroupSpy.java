/*
 * Copyright 2019 Uppsala University Library
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
package se.uu.ub.cora.bookkeeper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import se.uu.ub.cora.data.DataAtomic;
import se.uu.ub.cora.data.DataAttribute;
import se.uu.ub.cora.data.DataElement;
import se.uu.ub.cora.data.DataGroup;

public class DataGroupSpy implements DataGroup {

	public String nameInData;
	public List<DataElement> children = new ArrayList<>();
	public Map<String, String> attributes = new HashMap<>();
	public List<DataGroup> groupsWithNameInData = new ArrayList<>();
	public Map<String, String> atomicValues = new HashMap<>();
	public Map<String, DataGroup> dataGroups = new HashMap<>();
	public Map<String, List<DataGroup>> dataGroupsAsList = new HashMap<>();
	private String repeatId;
	public Map<String, Integer> numOfGetAllGroupsWithNameInDataToReturn = new HashMap<>();

	public DataGroupSpy(String nameInData) {
		this.nameInData = nameInData;
	}

	@Override
	public String getRepeatId() {
		return repeatId;
	}

	@Override
	public String getNameInData() {
		return nameInData;
	}

	@Override
	public String getFirstAtomicValueWithNameInData(String nameInData) {
		return atomicValues.get(nameInData);
	}

	@Override
	public DataGroup getFirstGroupWithNameInData(String childNameInData) {
		if (dataGroups.containsKey(childNameInData)) {
			return dataGroups.get(childNameInData);
		}
		return new DataGroupSpy(childNameInData);
	}

	@Override
	public void addChild(DataElement dataElement) {
		if (dataElement instanceof DataAtomicSpy) {
			DataAtomicSpy atomicSpyChild = (DataAtomicSpy) dataElement;
			atomicValues.put(atomicSpyChild.nameInData, atomicSpyChild.value);

		} else if (dataElement instanceof DataGroup) {
			DataGroup dataGroup = (DataGroup) dataElement;
			String dataGroupNameInData = dataGroup.getNameInData();
			dataGroups.put(dataGroupNameInData, dataGroup);

			if (!dataGroupsAsList.containsKey(dataGroupNameInData)) {
				dataGroupsAsList.put(dataGroupNameInData, new ArrayList<>());
			}
			dataGroupsAsList.get(dataGroupNameInData).add(dataGroup);

		}
		children.add(dataElement);
	}

	@Override
	public List<DataElement> getChildren() {
		return children;
	}

	@Override
	public boolean containsChildWithNameInData(String nameInData) {
		if (atomicValues.containsKey(nameInData) || dataGroups.containsKey(nameInData)) {
			return true;
		}
		return false;
	}

	@Override
	public void setRepeatId(String repeatId) {
		this.repeatId = repeatId;

	}

	@Override
	public void addAttributeByIdWithValue(String id, String value) {
		attributes.put(id, value);
	}

	@Override
	public Map<String, String> getAttributes() {
		return attributes;
	}

	@Override
	public DataElement getFirstChildWithNameInData(String nameInData) {
		if (dataGroups.containsKey(nameInData)) {
			return dataGroups.get(nameInData);
		} else if (atomicValues.containsKey(nameInData)) {
			return new DataAtomicSpy(nameInData, atomicValues.get(nameInData));
		}
		DataGroupSpy dataGroupSpy = new DataGroupSpy(nameInData);
		if ("refCollection".contentEquals(nameInData)) {
			dataGroupSpy.addChild(new DataAtomicSpy("linkedRecordId", "someSpyLinkedRecordId"));
		}
		return dataGroupSpy;
	}

	@Override
	public List<DataGroup> getAllGroupsWithNameInData(String nameInData) {
		List<DataGroup> matchingDataGroups = new ArrayList<>();
		if (numOfGetAllGroupsWithNameInDataToReturn.containsKey(nameInData)) {
			for (int i = 0; i < numOfGetAllGroupsWithNameInDataToReturn.get(nameInData); i++) {
				DataGroupSpy dataGroupSpy = new DataGroupSpy(nameInData);
				possiblyAddChildren(nameInData, dataGroupSpy);
				matchingDataGroups.add(dataGroupSpy);
			}
		} else if (dataGroupsAsList.containsKey(nameInData)) {
			matchingDataGroups.addAll(dataGroupsAsList.get(nameInData));
		} else if (dataGroups.containsKey(nameInData)) {
			matchingDataGroups.add(dataGroups.get(nameInData));
		}
		return matchingDataGroups;
	}

	private void possiblyAddChildren(String nameInData, DataGroupSpy dataGroupSpy) {
		if ("childRefCollectTerm".equals(nameInData)) {
			dataGroupSpy.addChild(new DataAtomicSpy("linkedRecordId", "someLinkedRecordIdFromSpy"));
			dataGroupSpy.addAttributeByIdWithValue("type", "someAttributeTypeFromSpy");
		} else if ("ref".equals(nameInData)) {
			dataGroupSpy.addChild(new DataAtomicSpy("linkedRecordId", "someLinkedRecordIdFromSpy"));
		}

	}

	@Override
	public String getAttribute(String attributeId) {
		return attributes.get(attributeId);
	}

	@Override
	public List<DataAtomic> getAllDataAtomicsWithNameInData(String childNameInData) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeFirstChildWithNameInData(String childNameInData) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<DataGroup> getAllGroupsWithNameInDataAndAttributes(String childNameInData,
			DataAttribute... childAttributes) {
		// TODO Auto-generated method stub
		return null;
	}

}
