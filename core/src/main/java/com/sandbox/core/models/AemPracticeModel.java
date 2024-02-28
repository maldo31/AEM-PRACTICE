package com.sandbox.core.models;

import java.util.List;
import javax.inject.Inject;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = {Resource.class, SlingHttpServletRequest.class},
    defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AemPracticeModel {

  @Getter
  private String title;

  @Getter
  private String damReference;

  @Getter
  private boolean backgroundImage;

  @Getter
  private List<NavigationItemModel> navigationItems;

  @Inject
  public AemPracticeModel(@ChildResource(name = "./navigationSection")
  List<NavigationItemModel> navigationItems,
      @ValueMapValue(name = "title") @Default(values = StringUtils.EMPTY)
      String title,
      @ValueMapValue(name = "damReference") @Default(values = StringUtils.EMPTY)
      String damReference,
      @ValueMapValue(name = "backgroundImage")
      boolean backgroundImage){

    this.title = title;
    this.damReference = damReference;
    this.navigationItems = navigationItems;
    this.backgroundImage = backgroundImage;
    }
  }





