package com.tadashboard.objects;

import com.tadashboard.helpers.StringHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Page {
    private String pageName;
    private String parentPageValue;
    private String numberOfColumnsValue;
    private String displayAfterValue;
    private boolean isPublic;

    public static Page createPublicPage(String pageName) {
        return new Page(pageName, "", "", "", true);
    }

    public static Page createPublicPageWithRandomName() {
        return createPublicPage(StringHelper.getRandomPageName());
    }

}
