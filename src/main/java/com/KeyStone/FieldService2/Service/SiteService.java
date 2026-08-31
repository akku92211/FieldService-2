package com.KeyStone.FieldService2.Service;

import java.util.List;

import com.KeyStone.FieldService2.Entity.Site;

public interface SiteService {
	public Site create(Site site);
	public Site updateSite(Long id,Site siteDetails);
	public Site getSite(Long id);
	public List<Site>getSiteByCustomer(Long customerId);
	public Site deleteSite(Long id);


}
