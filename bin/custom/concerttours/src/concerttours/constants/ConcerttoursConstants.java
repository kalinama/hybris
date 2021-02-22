/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company. All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package concerttours.constants;

/**
 * Global class for all Concerttours constants. You can add global constants for your extension into this class.
 */
public final class ConcerttoursConstants extends GeneratedConcerttoursConstants
{
	public static final Long ALBUM_SALES_BOUNDARY_VALUE = 5000L;

	public static final String EXTENSIONNAME = "concerttours";
	public static final String CATALOG_ID = "concertoursProductCatalog";
	public static final String CATALOG_VERSION_NAME = "Online";

	public static final String DEFAULT_BAND_IMAGE = "defaultBandImage";

	private ConcerttoursConstants()
	{
		//empty to avoid instantiating this constant class
	}

	// implement here constants used by this extension

    public static final String PLATFORM_LOGO_CODE = "concerttoursPlatformLogo";
}
