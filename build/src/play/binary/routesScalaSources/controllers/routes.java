
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/Lawrence/playspace/zaggle/conf/routes
// @DATE:Thu Feb 08 20:32:58 KST 2018

package controllers;

import router.RoutesPrefix;

public class routes {
  
  public static final controllers.ReverseAssets Assets = new controllers.ReverseAssets(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseCompetitionController CompetitionController = new controllers.ReverseCompetitionController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseHelloController HelloController = new controllers.ReverseHelloController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseApplicationController ApplicationController = new controllers.ReverseApplicationController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseApiHelpController ApiHelpController = new controllers.ReverseApiHelpController(RoutesPrefix.byNamePrefix());
  public static final controllers.ReverseBithumbController BithumbController = new controllers.ReverseBithumbController(RoutesPrefix.byNamePrefix());

  public static class javascript {
    
    public static final controllers.javascript.ReverseAssets Assets = new controllers.javascript.ReverseAssets(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseCompetitionController CompetitionController = new controllers.javascript.ReverseCompetitionController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseHelloController HelloController = new controllers.javascript.ReverseHelloController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseApplicationController ApplicationController = new controllers.javascript.ReverseApplicationController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseApiHelpController ApiHelpController = new controllers.javascript.ReverseApiHelpController(RoutesPrefix.byNamePrefix());
    public static final controllers.javascript.ReverseBithumbController BithumbController = new controllers.javascript.ReverseBithumbController(RoutesPrefix.byNamePrefix());
  }

}
