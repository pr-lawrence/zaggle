
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/Lawrence/playspace/zaggle/conf/routes
// @DATE:Thu Feb 08 20:32:58 KST 2018

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:7
  HelloController_0: controllers.HelloController,
  // @LINE:9
  BithumbController_1: controllers.BithumbController,
  // @LINE:12
  CompetitionController_2: controllers.CompetitionController,
  // @LINE:16
  Assets_3: controllers.Assets,
  // @LINE:19
  ApiHelpController_5: controllers.ApiHelpController,
  // @LINE:20
  ApplicationController_4: controllers.ApplicationController,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:7
    HelloController_0: controllers.HelloController,
    // @LINE:9
    BithumbController_1: controllers.BithumbController,
    // @LINE:12
    CompetitionController_2: controllers.CompetitionController,
    // @LINE:16
    Assets_3: controllers.Assets,
    // @LINE:19
    ApiHelpController_5: controllers.ApiHelpController,
    // @LINE:20
    ApplicationController_4: controllers.ApplicationController
  ) = this(errorHandler, HelloController_0, BithumbController_1, CompetitionController_2, Assets_3, ApiHelpController_5, ApplicationController_4, "/")

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HelloController_0, BithumbController_1, CompetitionController_2, Assets_3, ApiHelpController_5, ApplicationController_4, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """hello""", """controllers.HelloController.l7check"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """bithumb""", """controllers.BithumbController.index"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """competition""", """controllers.CompetitionController.list"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """competition/""" + "$" + """id<[^/]+>""", """controllers.CompetitionController.get(id:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """swagger.json""", """controllers.ApiHelpController.getResources"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """docs/""", """controllers.ApplicationController.redirectDocs"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """docs/""" + "$" + """file<.+>""", """controllers.Assets.at(path:String = "/public/swagger-ui", file:String)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:7
  private[this] lazy val controllers_HelloController_l7check0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("hello")))
  )
  private[this] lazy val controllers_HelloController_l7check0_invoker = createInvoker(
    HelloController_0.l7check,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HelloController",
      "l7check",
      Nil,
      "GET",
      this.prefix + """hello""",
      """ An example controller showing a sample home page""",
      Seq()
    )
  )

  // @LINE:9
  private[this] lazy val controllers_BithumbController_index1_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("bithumb")))
  )
  private[this] lazy val controllers_BithumbController_index1_invoker = createInvoker(
    BithumbController_1.index,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.BithumbController",
      "index",
      Nil,
      "POST",
      this.prefix + """bithumb""",
      """""",
      Seq()
    )
  )

  // @LINE:12
  private[this] lazy val controllers_CompetitionController_list2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("competition")))
  )
  private[this] lazy val controllers_CompetitionController_list2_invoker = createInvoker(
    CompetitionController_2.list,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CompetitionController",
      "list",
      Nil,
      "GET",
      this.prefix + """competition""",
      """ Competition""",
      Seq()
    )
  )

  // @LINE:13
  private[this] lazy val controllers_CompetitionController_get3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("competition/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_CompetitionController_get3_invoker = createInvoker(
    CompetitionController_2.get(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.CompetitionController",
      "get",
      Seq(classOf[String]),
      "GET",
      this.prefix + """competition/""" + "$" + """id<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:16
  private[this] lazy val controllers_Assets_versioned4_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned4_invoker = createInvoker(
    Assets_3.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )

  // @LINE:19
  private[this] lazy val controllers_ApiHelpController_getResources5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("swagger.json")))
  )
  private[this] lazy val controllers_ApiHelpController_getResources5_invoker = createInvoker(
    ApiHelpController_5.getResources,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApiHelpController",
      "getResources",
      Nil,
      "GET",
      this.prefix + """swagger.json""",
      """ Swagger API""",
      Seq()
    )
  )

  // @LINE:20
  private[this] lazy val controllers_ApplicationController_redirectDocs6_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("docs/")))
  )
  private[this] lazy val controllers_ApplicationController_redirectDocs6_invoker = createInvoker(
    ApplicationController_4.redirectDocs,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.ApplicationController",
      "redirectDocs",
      Nil,
      "GET",
      this.prefix + """docs/""",
      """""",
      Seq()
    )
  )

  // @LINE:21
  private[this] lazy val controllers_Assets_at7_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("docs/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_at7_invoker = createInvoker(
    Assets_3.at(fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "at",
      Seq(classOf[String], classOf[String]),
      "GET",
      this.prefix + """docs/""" + "$" + """file<.+>""",
      """""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:7
    case controllers_HelloController_l7check0_route(params@_) =>
      call { 
        controllers_HelloController_l7check0_invoker.call(HelloController_0.l7check)
      }
  
    // @LINE:9
    case controllers_BithumbController_index1_route(params@_) =>
      call { 
        controllers_BithumbController_index1_invoker.call(BithumbController_1.index)
      }
  
    // @LINE:12
    case controllers_CompetitionController_list2_route(params@_) =>
      call { 
        controllers_CompetitionController_list2_invoker.call(CompetitionController_2.list)
      }
  
    // @LINE:13
    case controllers_CompetitionController_get3_route(params@_) =>
      call(params.fromPath[String]("id", None)) { (id) =>
        controllers_CompetitionController_get3_invoker.call(CompetitionController_2.get(id))
      }
  
    // @LINE:16
    case controllers_Assets_versioned4_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned4_invoker.call(Assets_3.versioned(path, file))
      }
  
    // @LINE:19
    case controllers_ApiHelpController_getResources5_route(params@_) =>
      call { 
        controllers_ApiHelpController_getResources5_invoker.call(ApiHelpController_5.getResources)
      }
  
    // @LINE:20
    case controllers_ApplicationController_redirectDocs6_route(params@_) =>
      call { 
        controllers_ApplicationController_redirectDocs6_invoker.call(ApplicationController_4.redirectDocs)
      }
  
    // @LINE:21
    case controllers_Assets_at7_route(params@_) =>
      call(Param[String]("path", Right("/public/swagger-ui")), params.fromPath[String]("file", None)) { (path, file) =>
        controllers_Assets_at7_invoker.call(Assets_3.at(path, file))
      }
  }
}
