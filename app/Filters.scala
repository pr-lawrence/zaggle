import javax.inject.Inject

import filters.LoggingFilter
import play.api.http.DefaultHttpFilters
import play.api.http.EnabledFilters
import play.filters.gzip.GzipFilter

/**
  *
  * @author Lawrence
  * @since 2018. 2. 4.
  * @note
  * @version 0.1.0
  */
class Filters @Inject()(
                         defaultFilters: EnabledFilters,
                         gzip: GzipFilter,
                         log: LoggingFilter
                       ) extends DefaultHttpFilters(defaultFilters.filters :+ gzip :+ log: _*)