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
package tours.setup;

import static tours.constants.ToursConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import tours.constants.ToursConstants;
import tours.service.ToursService;


@SystemSetup(extension = ToursConstants.EXTENSIONNAME)
public class ToursSystemSetup
{
	private final ToursService toursService;

	public ToursSystemSetup(final ToursService toursService)
	{
		this.toursService = toursService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		toursService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return ToursSystemSetup.class.getResourceAsStream("/tours/sap-hybris-platform.png");
	}
}
