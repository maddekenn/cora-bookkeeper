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

package se.uu.ub.cora.bookkeeper.validator;

import se.uu.ub.cora.bookkeeper.data.DataElement;
import se.uu.ub.cora.bookkeeper.data.DataGroup;
import se.uu.ub.cora.bookkeeper.metadata.RecordLink;

public class DataRecordLinkValidator implements DataElementValidator {

	private static final String LINKED_REPEAT_ID = "linkedRepeatId";
	private static final String LINKED_RECORD_TYPE = "linkedRecordType";
	private ValidationAnswer validationAnswer;
	private DataGroup dataRecordLink;
	private RecordLink recordLink;

	public DataRecordLinkValidator(RecordLink dataLink) {
		this.recordLink = dataLink;
	}

	@Override
	public ValidationAnswer validateData(DataElement dataElement) {
		validationAnswer = new ValidationAnswer();
		dataRecordLink = (DataGroup) dataElement;
		validateNameInData();
		validateRecordType();
		validateRecordId();
		validateNoLinkedPath();
		validateLinkedRepeatId();
		return validationAnswer;
	}

	private void validateNameInData() {
		if (nameInDataIsEmpty()) {
			validationAnswer.addErrorMessage("DataRecordLink must have a nonempty nameInData");
		}
	}

	private boolean nameInDataIsEmpty() {
		return dataRecordLink.getNameInData().isEmpty();
	}

	private void validateRecordType() {
		if (recordTypeIsEmpty()) {
			validationAnswer.addErrorMessage(
					createNameInDataMessagePart() + " must have an nonempty recordType as child.");
		}
		if (incomingRecordTypeNotSameAsSpecifiedInMetadata()) {
			validationAnswer.addErrorMessage(createNameInDataMessagePart()
					+ " must have an recordType:" + recordLink.getLinkedRecordType());
		}
	}

	private boolean recordTypeIsEmpty() {
		return !dataRecordLink.containsChildWithNameInData(LINKED_RECORD_TYPE)
				|| dataRecordLink.getFirstAtomicValueWithNameInData(LINKED_RECORD_TYPE).isEmpty();
	}

	private boolean incomingRecordTypeNotSameAsSpecifiedInMetadata() {
		String linkedRecordType = dataRecordLink.getFirstAtomicValueWithNameInData(LINKED_RECORD_TYPE);
		return !linkedRecordType.equals(recordLink.getLinkedRecordType());
	}

	private void validateRecordId() {
		if (recordIdIsEmpty()) {
			validationAnswer.addErrorMessage(
					createNameInDataMessagePart() + " must have an nonempty recordId as child.");
		}
	}

	private boolean recordIdIsEmpty() {
		return !dataRecordLink.containsChildWithNameInData("linkedRecordId")
				|| dataRecordLink.getFirstAtomicValueWithNameInData("linkedRecordId").isEmpty();
	}

	private String createNameInDataMessagePart() {
		return "DataRecordLink with nameInData:" + dataRecordLink.getNameInData();
	}

	private void validateNoLinkedPath() {
		if (thereIsALinkedPath()) {
			validationAnswer.addErrorMessage(
					createNameInDataMessagePart() + " should not have a linkedPath");
		}
	}

	private boolean thereIsALinkedPath() {
		return dataRecordLink.containsChildWithNameInData("linkedPath");
	}

	private void validateLinkedRepeatId() {
		if (dataShouldContainALinkedRepeatId()) {
			validateHasLinkedRepeatId();
		} else {
			validateDoesNotHaveLinkedRepeatId();
		}
	}

	private boolean dataShouldContainALinkedRepeatId() {
		return recordLink.getLinkedPath() != null;
	}

	private void validateHasLinkedRepeatId() {
		if (linkedRepeatIdIsEmpty()) {
			validationAnswer.addErrorMessage(
					createNameInDataMessagePart() + " should have a linkedRepeatId");
		}
	}

	private boolean linkedRepeatIdIsEmpty() {
		return (!dataRecordLink.containsChildWithNameInData(LINKED_REPEAT_ID))
				|| dataRecordLink.getFirstAtomicValueWithNameInData(LINKED_REPEAT_ID).isEmpty();
	}

	private void validateDoesNotHaveLinkedRepeatId() {
		if (linkedRepeatIdIsNotNull()) {
			validationAnswer.addErrorMessage(
					createNameInDataMessagePart() + " should not have a linkedRepeatId");
		}
	}

	private boolean linkedRepeatIdIsNotNull() {
		return dataRecordLink.containsChildWithNameInData(LINKED_REPEAT_ID);
	}

}
