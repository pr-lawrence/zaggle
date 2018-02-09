
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/Lawrence/playspace/zaggle/conf/routes
// @DATE:Thu Feb 08 20:32:58 KST 2018

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:7
package controllers {

  // @LINE:16
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:21
    def at(file:String): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public/swagger-ui"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "docs/" + implicitly[play.api.mvc.PathBindable[String]].unbind("file", file))
    }
  
    // @LINE:16
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }

  // @LINE:12
  class ReverseCompetitionController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:12
    def list(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "competition")
    }
  
    // @LINE:13
    def get(id:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "competition/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("id", id)))
    }
  
  }

  // @LINE:7
  class ReverseHelloController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:7
    def l7check(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "hello")
    }
  
  }

  // @LINE:20
  class ReverseApplicationController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:20
    def redirectDocs(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "docs/")
    }
  
  }

  // @LINE:19
  class ReverseApiHelpController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:19
    def getResources(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "swagger.json")
    }
  
  }

  // @LINE:9
  class ReverseBithumbController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:9
    def index(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "bithumb")
    }
  
  }


}
