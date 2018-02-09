
// @GENERATOR:play-routes-compiler
// @SOURCE:/Users/Lawrence/playspace/zaggle/conf/routes
// @DATE:Thu Feb 08 20:32:58 KST 2018


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
