import javax.inject.Inject;

import play.filters.hosts.AllowedHostsFilter;
import play.http.DefaultHttpFilters;

public class Filters extends DefaultHttpFilters {

  @Inject
  public Filters(AllowedHostsFilter allowedHostsFilter) {
    super(allowedHostsFilter);
  }
}