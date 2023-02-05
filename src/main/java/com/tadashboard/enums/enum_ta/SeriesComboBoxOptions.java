package com.tadashboard.enums.enum_ta;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SeriesComboBoxOptions {
    NAME("Name"), LOCATION("Location"), DESCRIPTION("Description"), REVISION_TIMESTAMP("Revision Timestamp"),
    ASSIGNED_USER("Assigned user"), LAST_UPDATE_DATE("Last update date"), LAST_UPDATED_BY("Last updated by"),
    CREATION_DATE("Creation date"), CREATED_BY("Created by"), NOTES("  Notes"), URL("URL");

    private final String value;
}
