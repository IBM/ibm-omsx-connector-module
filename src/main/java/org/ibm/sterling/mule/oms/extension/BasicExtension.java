package org.ibm.sterling.mule.oms.extension;

import org.ibm.sterling.mule.oms.config.BasicConfiguration;
import org.mule.runtime.extension.api.annotation.Configurations;
import org.mule.runtime.extension.api.annotation.Extension;
import org.mule.runtime.extension.api.annotation.dsl.xml.Xml;
import org.mule.runtime.extension.api.annotation.license.RequiresEntitlement;


/**
 * This is the main class of an extension, is the entry point from which configurations, connection providers, operations
 * and sources are going to be declared.
 */
@Xml(prefix = "ibm-omsx")
@Extension(name = "IBM-OMSX")
@Configurations(BasicConfiguration.class)
@RequiresEntitlement(name = "IBM Corp")
public class BasicExtension {

}
