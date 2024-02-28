package com.sandbox.core.models;

import javax.inject.Inject;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(
    adaptables = {
        Resource.class,
        SlingHttpServletRequest.class
    }
)
public class NavigationItemModel {

  @Getter
  private final String pageTitle;
  @Getter
  private final String pagePath;

  @Inject
  public NavigationItemModel(@ValueMapValue(name = "pageTitle") @Default(values = StringUtils.EMPTY)
  String pageTitle,
      @ValueMapValue(name = "pagePath") @Default(values = StringUtils.EMPTY)
      String pagePath) {
    this.pageTitle =  "TEST_" + pageTitle;
    this.pagePath = pagePath;

  }
}
