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

package se.uu.ub.cora.bookkeeper.metadata;

import java.util.HashMap;
import java.util.Map;

/**
 * MetadataHolder holds all information about MetadataFormats MetadataGroups and
 * MetadataVariables
 * 
 * @author <a href="mailto:olov.mckie@ub.uu.se">Olov McKie</a>
 *
 * @since 0.1
 *
 */
public class MetadataHolder {

	private Map<String, MetadataElement> metadata = new HashMap<>();

	/**
	 * addMetadataElement adds an element to the internal holder of elements
	 * 
	 * @param metadataElement
	 *            A MetadataElement to add to the internal holder
	 */
	public void addMetadataElement(MetadataElement metadataElement) {
		metadata.put(metadataElement.getId(), metadataElement);
	}

	/**
	 * getMetadataElement returns the requested MetadataElement based on the
	 * parameter elementId
	 * 
	 * @param elementId
	 *            A String with the id of the Metadata element to get
	 * @return The requested MetadataElement
	 */
	public MetadataElement getMetadataElement(String elementId) {
		return metadata.get(elementId);
	}
}
